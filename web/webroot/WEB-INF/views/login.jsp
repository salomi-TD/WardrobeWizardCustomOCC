
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login Form</title>
  <script src="https://cdn.jsdelivr.net/npm/vue@2"></script>
  <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</head>
<body>
  <div id="app">
    <form @submit.prevent="submitForm">
      <label for="phone">Phone:</label>
      <input type="text" v-model="phone" id="phone">
      <br><br>
      <button type="button" @click="requestOTP">GET OTP</button>
      <div v-if="otpResponse">
    <p>OTP Response: {{ otpResponse }}</p>
  </div>
      <br><br>
      <label for="enteredOTP" v-if="showVerifyButton">Verify OTP:</label>
      <input type="text" v-model="enteredOTP" id="enteredOTP" v-if="showVerifyButton">
      <br><br>
      <button type="submit" v-if="showVerifyButton">Submit</button>
    </form>
    <div v-if="formSubmissionResponse">
    <p>Form Submission Response: {{ formSubmissionResponse }}</p>
  </div>
  </div>
  

  <script src="${pageContext.request.contextPath}/_ui/login-v-app.js"></script>
</body>
