<template>
  <div class="dashboard-display">
    <div class="title">
      <h2>Play Dates In Your Area</h2>
    <div v-if="error">{{ error }}</div>
    <table v-if="playdates.length" class="play-dates-table">
        <thead>
          <tr>
            <th>Event Date</th>
            <th>Event Time</th>
            <th>Event Location</th>
            <th>Organizer</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="playdate in playdates" :key="playdate.eventId">
            <td>{{ playdate.eventDate }}</td>
            <td>{{ convertTime(playdate.eventTime) }}</td>
            <td>{{ playdate.eventLocation }}</td>
            <td>{{ playdate.organizer }}</td>
          </tr>
        </tbody>
      </table>
    <div v-if="!playdates.length">No scheduled playdates found.</div>
    </div>
  </div>

</template>

<script>
import AuthService from "../services/AuthService"

export default {
  name: "dashboard-display",
  components: {},
  data() {
    return {
      error: null,
      playdates: []
    };
  },
  methods:{
    convertTime(date){
        let dateFormat = date.substring(0,23);
        console.log(dateFormat);
    // "2023-08-16T16:00:00.000Z:00"
    var isoDateTime = new Date(dateFormat);
    var localDateTime = isoDateTime.toLocaleTimeString();
    return localDateTime;
}
  },
  created() {
    AuthService.getScheduledPlaydates().then(response => {
      this.playdates=response.data;
      console.log(this.playdates)
    })
  }
};
</script>

<style scoped>
/* #profile-nav {
  display: flex;
  height: 50px;
  line-height: 50px;
  background-color: rgb(107, 62, 3);
}
.nav-link {
  display: inline-block;
  padding: 0 16px;
  color: white;
  text-decoration: none;
} */
.dashboard-display {
  background-color: #f2f0ee;
  min-height: 50vh;
  display: flex;
  flex-direction: row;
  flex: start;
}

.title {
  font-family: 'Poppins', sans-serif;
  font-size: 25px;
  color: #97533d;
  text-align: center;
  margin-left: 20px;
  margin-top: 50px;
}

.play-dates-table {
  border-collapse: collapse;
  margin-top: 20px;
  font-family: 'Poppins', sans-serif;
  width: 35%;
  margin-left: 35px;
}

.play-dates-table th {
  background-color: #e1a26a;
  border: 1px solid #dddddd;
  font-size: 25px;
  text-align: center;
  padding: 8px;
  color: white;
  white-space: nowrap;
}

.play-dates-table td {
  border: 1px solid #dddddd;
  padding: 8px;
  background-color: white;
  font-size: 20px;
  white-space: nowrap;
  color: black;
  text-align: center;
}

.play-dates-table tr:nth-child(even) {
  background-color: #cac8c8;
}

.play-dates-table tr:hover {
  background-color: #ddd;
}
</style>