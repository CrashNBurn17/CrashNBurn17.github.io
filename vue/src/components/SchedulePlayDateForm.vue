<template>
    <div id="schedule-playdate" class="schedule-playdate-center">
    <form @submit.prevent="createEvent()" class="schedule-playdate-form">
        <h1>Create a Play Date Event</h1>
        
        <div role="alert" v-if="this.$route.query.registration" class="alert alert-success">
            Your event has been created.
        </div>
        <div class="form-input-group">
            <label for="eventDate">Event Date</label>  
            <v-date-picker v-model="playdate.eventDate"></v-date-picker>
        </div>
        <div class="form-input-group">
            <label for="eventTime">Event Time</label>  
            <datetime v-model="playdate.eventTime" type="time" format="HH:mm:ss" :is-utc="true" use12-hour value-zone="America/New_York"></datetime>
        </div>
        <div class="form-input-group">
            <label for="eventLocation">Event Location</label>
            <select v-model="playdate.eventLocation" required class="event-location">
              <option v-for="park in parks" :key="park" :value="park">{{ park }}</option>
            </select>   
        </div>
        <div class="form-input-group">
            <label for="organizer">Organizer</label>  
            <input type="text" v-model="playdate.organizer" required class="form-input" placeholder="Enter Username"/>
        </div>

        <button type="submit" class="submit-btn">Create Event</button>
        
    </form>
    </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
    name: 'schedule-playdate',
    data() {
        return {
            playdate: {
                eventDate: null,
                eventTime: '00:00:00',
                eventLocation: 0,
                organizer: ''
            },
            parks: [
              'Allegheny Commons Dog Park',
              'Bernard Dog Run',
              'Dormont Dog Park',
              'Frick Park Off-Leash Exercise Area',
              'Hartwood Acres Park Off-Leash Dog Area',
              'Highland Park Off-Leash Dog Area',
              'Olympia Park Dog Run',
              'Penn Avenue Dog Park',
              'Riverfront Park Off-Leash Exercise Area',
              'West Mifflin Dog Park'
            ]
        };
    },
    methods: {
      createEvent() {
        const formattedEventTime = this.playdate.eventTime + ":00";
        let time = formattedEventTime.substring(0, 24) + 'Z';
        authService
          .createEvent({...this.playdate, eventTime: time})
          .then((response) => {
            console.log(response.status);
            if (response.status == 201) {
              this.$store.commit("SET_PLAYDATE", response.data.playdate);
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

.schedule-playdate-center {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
}

.schedule-playdate-form {
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
.schedule-playdate-form h1 {
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

.event-location {
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

</style>