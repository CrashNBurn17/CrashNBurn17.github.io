<template>
  <div id="login" class="login-center">
    <form v-on:submit.prevent="login" class="login-form">
      <h1 >Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials" class="alert alert-error">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration" class="alert alert-success">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus class="form-input" />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required class="form-input" />
      </div>
      <button type="submit" class="submit-btn">Sign in</button>
      <p>
      <router-link :to="{ name: 'register' }" class="register-link">Need an account? Sign up.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/dashboard");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
body {
  margin: 0;
  padding: 0;
}

.login-center {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
}

.login-form {
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
.login-form h1 {
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

.alert-error{
  background-color: #f44336;
}

.alert-success {
  background-color: #4caf50;
}

.register-link {
  font-size: 20px;
  color: black;
  text-decoration:none;
  transition: color 0.3s ease;
  border-bottom: 1px solid black;
}

.register-link.hover {
  color: black;
}
</style>