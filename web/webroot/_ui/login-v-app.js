const app = new Vue({
      el: '#app',
      data() {
        return {
          phone: '',
          enteredOTP: '',
          showVerifyButton: false,
          otpResponse: '',
    formSubmissionResponse: ''
        };
      },
     methods: {
  requestOTP() {
    const formData = new FormData();
    formData.append('phone', this.phone);

    fetch('https://localhost:9002/occ/v2/electronics/logIn', {
      method: 'POST',
      body: formData
    })
      .then(response => response.json())
      .then(data => {
        console.log('OTP received:', data);
        this.otpResponse = data;
        this.showVerifyButton = true;
      })
      .catch(error => {
        console.error('Error fetching OTP:', error);
      });
  },
  submitForm() {
    const formData = new FormData();
    formData.append('enteredOTP', this.enteredOTP);

    fetch('https://localhost:9002/occ/v2/electronics/verify', {
      method: 'POST',
      body: formData
    })
      .then(response => response.json())
      .then(data => {
        console.log('Form submitted successfully!', data);
      this.formSubmissionResponse = data;
if (this.formSubmissionResponse !== 'entered wrong OTP ') {  // Corrected comparison
  window.location.href = `https://localhost:9002/training/?phone=${encodeURIComponent(this.phone)}`;
}

      })
      .catch(error => {
        console.error('Error submitting form:', error);
        this.formSubmissionResponse = data;
      });
  }
}

    });