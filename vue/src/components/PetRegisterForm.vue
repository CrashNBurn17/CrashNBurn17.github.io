<template>
    <div id="pet-register" class="pet-register-center">
    <form @submit.prevent="petRegister()" class="pet-register-form">
        <h1>Register Pet</h1>
        
        <div role="alert" v-if="this.$route.query.registration" class="alert alert-success">
            Your pet has been registered.
        </div>
        <div class="form-input-group">
            <label for="petName">Pet Name</label>  
            <input type="text" v-model="pet.petName"  required class="form-input" placeholder="Enter Pet Name" />
        </div>
        <div class="form-input-group">
            <label for="petBreed">Pet Breed</label>
            <select v-model="pet.petBreed" required class="pet-breed">
              <option v-for="breed in breeds" :key="breed" :value="breed">{{ breed }}</option>
            </select>  
        </div>
        <div class="form-input-group">
            <label for="petPersonality">Pet Personality</label>
            <select v-model="pet.petPersonality" required class="pet-personality">
              <option v-for="personality in personalities " :key="personality" :value="personality">{{ personality }}</option>
            </select>  
        </div>
        <div class="form-input-group">
            <label for="petZipCode">Pet Zip Code</label>  
            <input type="text" v-model.number="pet.petLocation"  required class="form-input" />
        </div>

        <button type="submit" class="submit-btn">Register Pet</button>
        
    </form>
    </div>
</template>

<script>
import authService from "../services/AuthService"

export default {
    name: 'pet-register',
    data() {
        return {
            pet: {
                petName: '',
                petBreed: null,
                petPersonality: null,
                petLocation: 0,
            },
            breeds: ['Beagle', 'Boxer', 'Bulldog', 'French Bulldog', 'German Shepherd', 'Golden Retriever', 'Labrador', 'Pitbull', 'Poodle', 'Rottweiler', 'Siberian Husky'
            ],
            personalities: ['Adaptable', 'Affectionate', 'Curious', 'Energetic', 'Empathetic', 'Friendly', 'Intelligent', 'Loyal', 'Playful', 'Protective']
        };
    },
    methods: {
      petRegister() {
        authService
          .petRegister(this.pet)
          .then((response) => {
            console.log(response.status);
            if (response.status == 201) {
              this.$store.commit("SET_PET", response.data.pet);
              this.$router.push({
                path: '/dashboard',
                query: { registration: 'success' },
              });
            }
          })
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this pet.';
    },
}
    


</script>

<style>
body {
  margin: 0;
  padding: 0;
}

.pet-register-center {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
}

.pet-register-form {
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
.pet-register-form h1 {
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

.pet-breed {
  font-size: 20px;
  width: 100%;
  padding: 10px 15px;
  border: 1px solid #ddd;
  border-radius: 5px;
  transition: border-color 0.3s ease;
}

.pet-personality {
  font-size: 20px;
  width: 100%;
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
  background-color: #705a56;
  border:none;
  border-radius: 5px;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.submit:hover {
  background-color: #705a56;
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

/* .register-link {
  color: #705a56;
  text-decoration:none;
  transition: color 0.3s ease;
}

.register-link.hover {
  color: #705a56;
} */
</style>