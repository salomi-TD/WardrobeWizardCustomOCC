const wazardWrobe = Vue.createApp({
	data() {
		return {
			cameraFeed: null,
			captureButton: null,
			imageCanvas: null,
			uploadButton: null,
			processButton: true,
			openCameraButton: null,
			xhr: null,
			showCaptureButton: false,
			showUploadButton: false,
			Spinnerload: false,
			imageuploadBtn: false,
			uploadButtonVisible: false,
			showDivCam:false,
			EnableCamerabutton: true,
			showCaptureText: false,
			showAfterUpload:false,
			
		};
	},
	mounted() {
		this.cameraFeed = document.getElementById('camera-feed');
		this.captureButton = document.getElementById('capture-button');
		this.imageCanvas = document.getElementById('image-canvas');
		this.uploadButton = document.getElementById('upload-button');
		this.processButton = document.getElementById('process-button');
		this.openCameraButton = document.getElementById('open-camera-button');
		this.imageUploadBtn = document.getElementById('image-Upload-Btn');
	},
	methods: {
		// Methods go here

		async openCamera() {
			
			try {
				this.cameraStream = await navigator.mediaDevices.getUserMedia({
					video: true
				});
				this.$refs.cameraFeed.srcObject = this.cameraStream;
				this.showCaptureButton = true;
				this.EnableCamerabutton = false;
			} catch (error) {
				console.error('Error accessing camera:', error);
			}
		},
		showUploadButtons(event) {
                    // Get the input element
                    

                    // Check if a file has been selected
                    if (event.target.files.length  > 0) {
                        // Show the upload button
                        this.uploadButtonVisible = true;
                    } else {
                        // Hide the upload button
                        this.uploadButtonVisible = false;
                    }
                },
		cancelCamera() {
			try {
				// Stop the media stream when canceling
				if (this.cameraStream) {
					const tracks = this.cameraStream.getTracks();
					tracks.forEach(track => track.stop());
				}
				this.showCaptureButton = false;
				this.cameraStream = null; // Reset the camera stream
				this.$refs.cameraFeed.srcObject = null; // Update the camera feed
				this.EnableCamerabutton = true;
			} catch (error) {
				console.error('Error canceling camera:', error);
			}
		},
		clearCanvas() {
			// Clear the canvas
			const context = this.$refs.imageCanvas.getContext('2d');
			context.clearRect(0, 0, this.$refs.imageCanvas.width, this.$refs.imageCanvas.height);
			this.showUploadButton = false;
			this.showCaptureText = false;
		},

		captureImage() {
			const context = this.$refs.imageCanvas.getContext('2d');
			this.$refs.imageCanvas.width = this.$refs.cameraFeed.videoWidth;
			this.$refs.imageCanvas.height = this.$refs.cameraFeed.videoHeight;
			context.drawImage(
				this.$refs.cameraFeed,
				0,
				0,
				this.$refs.imageCanvas.width,
				this.$refs.imageCanvas.height
			);
			this.showUploadButton = true;
			this.showDivCam = true;
			this.showCaptureText = true;
			// Show the upload button
			/*this.$refs.uploadButton.style.display = 'block';*/
		},
		uploadImagecamer() {
			const imageData = this.$refs.imageCanvas.toDataURL('image/jpeg');
			const formData = new FormData();
			formData.append('file', this.dataURItoBlob(imageData));
			formData.append('fields', 'image');
			this.Spinnerload = true;
			fetch('https://localhost:9002/occ/v2/electronics/upload', {
					method: 'POST',
					body: formData,
				})
				.then(response => response.json())
				.then(data => {
					if (data.error) {
						console.error('Server error:', data.error);
					} else {
						console.log('Server response:', data.message);
						// Handle the response as needed
						this.Spinnerload = false;
						this.processButton = false;
						this.showAfterUpload = true;
						this.showCaptureText = false;
					}
				})
				.catch(error => {
					console.error('Error sending image data:', error);
				});
		},
		dataURItoBlob(dataURI) {
			const byteString = atob(dataURI.split(',')[1]);
			const mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
			const ab = new ArrayBuffer(byteString.length);
			const ia = new Uint8Array(ab);

			for (let i = 0; i < byteString.length; i++) {
				ia[i] = byteString.charCodeAt(i);
			}

			return new Blob([ab], {
				type: mimeString
			});
		},

		cancelUpload(event) {
			if (this.xhr && this.xhr.readyState === XMLHttpRequest.OPENED) {
				this.xhr.abort();
				const resultDiv = document.getElementById('result');
				resultDiv.innerHTML = `Upload canceled for ${event.target.dataset.fileName}.`;
			}
		},
		uploadImage() {
			const fileInput = document.getElementById('imageInput');
			const file = fileInput.files[0];

			if (!file) {
				alert('Please select a file for upload.');
				return;
			}
			this.Spinnerload = true;
			const fileName = file.name;

			const resultDiv = document.getElementById('result');
			resultDiv.innerHTML = `Uploading: ${fileName} (0.00%)`;
			resultDiv.classList.add('uploadstatus');

			const formData = new FormData();
			formData.append('file', file);
			formData.append('fields', 'image');

			this.xhr = new XMLHttpRequest();

			this.xhr.onload = () => {
				if (this.xhr.status === 200) {
					resultDiv.innerHTML = `Uploading: ${fileName} (100%)`;
					resultDiv.innerHTML = `Image ${fileName} uploaded successfully.`;
					resultDiv.classList.add('green');
					this.Spinnerload = false;
					this.processButton = false;
					this.showAfterUpload = true;
				} else {
					resultDiv.innerHTML = `Error uploading image ${fileName}.`;
				}
			};

			this.xhr.upload.onprogress = (event) => {
				if (event.lengthComputable) {
					const progress = (event.loaded / event.total) * 100;
					resultDiv.innerHTML = `Uploading: ${fileName} (${progress.toFixed(2)}%)`;

					const cancelButton = document.createElement('button');
					cancelButton.classList.add('cancel-button');
					cancelButton.innerText = 'X';
					cancelButton.dataset.fileName = fileName; // Set the file name as a data attribute
					cancelButton.addEventListener('click', this.cancelUpload.bind(this)); // Bind the method to this instance
					resultDiv.appendChild(cancelButton);
				}
			};

			this.xhr.onerror = () => {
				resultDiv.innerHTML = `Error uploading image ${fileName}.`;
			};

			this.xhr.open('post', 'https://localhost:9002/occ/v2/electronics/upload', true);
			this.xhr.send(formData);
		},
		submitUrl() {
			const formData = new FormData();
			formData.append('url', this.url);
			this.Spinnerload = true;
			fetch('https://localhost:9002/occ/v2/electronics/getThroughURL', {
					method: 'POST',
					body: formData
				})
				.then(response => response.json())
				.then(data => {
					if (data.error) {
						console.error('Server error:', data.error);
					} else {
						console.log('Server response:', data.message);
						this.Spinnerload = false;
						this.processButton = false;
					}
				})
				.catch(error => console.error('Error:', error));
		}
	}
});

wazardWrobe.mount('#wazardWrobe-app');