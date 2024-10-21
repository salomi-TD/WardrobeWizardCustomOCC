<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Image Upload and Camera Capture</title>

 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
   
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/welcome.css" />
<script src="https://unpkg.com/vue@3.2.33/dist/vue.global.js"></script>
<script src="https://unpkg.com/axios@1.1.2/dist/axios.min.js"></script>
<script type="module" src="${pageContext.request.contextPath}/_ui/wizard-v-app.js"></script>
</head>
<body>

	<div id="wazardWrobe-app">
		<div class="fluid-container text-center ">
			<img alt="" class="m-auto img-responsive p-5" src="https://ik.imagekit.io/Vijay69/image_h1TQ5wOcA.png?updatedAt=1695987806259" />
		</div>
		<div class="container p-5 w-50	 bg-white">
			<div class="form-container">
				<div class="section_heading d-flex">
					<img alt="" class="img-responsive mr-2" style="width: 40px;height:40px;"
						src="https://ik.imagekit.io/Vijay69/__icon__media_image_list__s1cOpsGmY.png?updatedAt=1695981233307"/>
					<div>
						<h5 class="section_headinglabel mb-1 bold" >Select Image</h5>
						<h6 class="section_headinglabel text-secondary">Pick Image and Upload</h6>
						<button type="submit" class="choosebtn" id="image-Upload-Btn" v-if="imageuploadBtn" >Capture and Upload</button>
					</div>
				</div>
				<div class="pickimage mt-3 p-5">
					<img alt="" class="img-responsive p-2" src="https://ik.imagekit.io/Vijay69/feather_upload-cloud_Lt4l9vpch.png?updatedAt=1695981289556" />
					<div>
						<div class="text-dark py-3">Select a file or drag and drophere</div>
						<div class="text-secondary pb-3">JPG, PNG, file size no morethan 10MB</div>
					</div>
					<form  id="imageUploadForm" class="mb-3" enctype="multipart/form-data">
						<input type="file" id="imageInput" name="imageFile" @change="showUploadButtons" accept="image/*"> <label for="imageInput" class="choosebtn">Choose Image</label>
						<button type="button" v-show="uploadButtonVisible"class="ml-2 choosebtn" @click="uploadImage()">Upload</button>
					</form>
					<div id="result" class="w-75 m-auto mt-2 rounded bg-white p-3 d-none "></div>
				</div>
			</div>
			<div class="form-container">
				<div class="section_heading d-flex">
					<img alt="" style="width: 40px; height: 35px;" class="img-responsive mt-2 mr-2" src="https://ik.imagekit.io/Vijay69/FirstPAgeImages/__icon__camera__E6AQuAvzDg.png?updatedAt=1696857964458" />
					<div>
						<h5 class="section_headinglabel mb-1 bold">Capture Image</h5>
						<h6 class="section_headinglabel text-secondary">click Image and Upload</h6>
					</div>
				</div>
				<div class="pickimage mt-3 p-5">
					<img alt="" class="img-responsive p-2" src="https://ik.imagekit.io/Vijay69/FirstPAgeImages/Group_wC9fLSH_5.png?updatedAt=1696857984333" />
					<div>
						<div class="text-dark py-3">Click here to capture image</div>
					</div>
					<button v-show="EnableCamerabutton" class="choosebtn" @click="openCamera">Open Camera</button>
					<div class="position-relative">
					<video ref="cameraFeed" class="asdf" autoplay></video>
					<button v-if="showCaptureButton" class="choosebtn2" @click="cancelCamera">Close Camera</button>
					<button v-if="showCaptureButton" class="choosebtn ml-2 " @click="captureImage">Capture</button>
					
					<form style="    position: absolute;    top: 0;" id="upload-form" action="https://localhost:9002/occ/v2/electronics/upload" method="post" @submit.prevent="uploadImagecamer">
						<canvas ref="imageCanvas"></canvas>
						<div class="d-flex bg-white" v-if="showCaptureText" style="border: 1px solid green;padding: 10px; align-items: center;
">
						<div > Image is captured Do you want to  </div>
						<button type="submit" class="choosebtn mx-2" v-if="showUploadButton" > Upload</button>
						<button v-if="showUploadButton" class="choosebtn2" @click="clearCanvas">Cancel</button>
						</div>
						<div v-if="showAfterUpload" class=" bg-white">successfully Upload Click on Next to see result</div>
					</form>
					</div>
				</div>
			</div>

			<div>
				<div class="section_heading d-flex">
					<img style="width: 40px; height: 40px;" alt="" class="img-responsive mr-2" src="https://ik.imagekit.io/Vijay69/__icon__link__pmDMTfo-N.png?updatedAt=1695987846503" />
					<div>
						<h5 class="section_headinglabel mb-1">Import from URl</h5>
						<h6 class="section_headinglabel text-secondary">Copy link address and paste below</h6>
					</div>
				</div>
				<div class="pickimage mt-3 text-left">
					<form id="urlForm">
						<input type="text" class="w-auto" id="url" name="url" v-model="url" required>
						<button class="choosebtn" type="submit" @click.prevent="submitUrl">Submit</button>
					</form>

				</div>
			</div>
			<div class="form-container">
				<h2>Process Image</h2>
				<form id="process-form" action="/training/dream" method="GET" enctype="multipart/form-data">
					<button id="process-button" :disabled="processButton">Next</button>
				</form>
			</div>
			<div v-if="Spinnerload" class="spinner-container " role="status">
			<div class="spinner-border">
				<span class="sr-only">Loading...</span>
				</div>
			</div>
		</div>
	</div>
	<div>
        <c:forEach var="url" items="${urls}">
            <li>
                <img src="${url}" alt="Image">
            </li>
        </c:forEach>
   	</div>
	
</body>
</html>