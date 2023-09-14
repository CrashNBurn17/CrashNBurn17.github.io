<template>
  <div id="register" class="register-center">
    <form @submit.prevent="register" class="register-form">
      <h1>Create Account</h1>
      <div role="alert" v-if="registrationErrors" class="alert alert-error">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus class="form-input" />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required class="form-input"/>
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required class="form-input" />
      </div>
      <button type="submit" class="submit-btn">Create Account</button>
      <p><router-link :to="{ name: 'login' }" class="account-exists">Already have an account? Log in.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.register-center {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center; 
  height: 100vh;
}

.register-form {
  font-family: 'Poppins', sans-serif;
  display:flex;
  flex-direction: column;
  align-items: center;
  width: 500px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f0f0f0;
  border-radius: 10px;
  box-shadow:0 10px 20px rgba(0,0,0,0.1);
}
.register-form h1 {
  color: #333;
  margin-bottom: 20px;
}

.form-input-group {
  font-size: 20px;
  padding: 10px 15px;
  width: 100%;
  margin-bottom: 15px;
  align-items: center;
}

.form-input {
  font-size: 20px;
  width: 94%;
  padding: 10px 15px;
  border: 1px solid #ddd;
  border-radius: 5px;
  transition: border-color 0.3s ease;
}

.form-input:focus {
  border-color: #705a56;
  outline: none;

}

.submit-btn {
  font-size: 20px;
  width: 100%;
  padding: 10px 15px;
  background-color: #a5b462;
  border:none;
  border-radius: 5px;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.submit:hover {
  background-color: #a5b462;
}

.alert {
  width: 100%;
  padding: 10px;
  color: white;
  border-radius: 5px;
  text-align: center;
  margin-bottom: 15px;
}

.account-exists {
  font-size: 18px;
  color: black;
  text-decoration:none;
  transition: color 0.3s ease;
  border-bottom: 1px solid black;
}

</style>
