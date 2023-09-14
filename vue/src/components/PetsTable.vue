<template>
  <div class="dashboard-display">
    <div class="title">
      <h2>Registered Pets</h2>
    <div v-if="error">{{ error }}</div>
    <table v-if="pets.length" class="pets-table">
        <thead>
          <tr>
            <th>Pet Name</th>
            <th>Pet Breed</th>
            <th>Pet Personality</th>
            <th>Pet Location</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="pet in pets" :key="pet.petId">
            <td>{{ pet.petName }}</td>
            <td>{{ pet.petBreed }}</td>
            <td>{{ pet.petPersonality }}</td>
            <td>{{ pet.petLocation }}</td>
          </tr>
        </tbody>
      </table>
    <div v-if="!pets.length">No registered pets found.</div>
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
      pets: []
    };
  },

  created() {
    AuthService.getRegisteredPets().then(response => {
      this.pets=response.data;
    });
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
  flex: end;
  /* flex-wrap: nowrap; */
}

.title {
  font-family: 'Poppins', sans-serif;
  font-size: 25px;
  color: #97533d;
  text-align: center;
  margin-left: 20px;
  margin-top: 50px;
}

.pets-table {
  border-collapse: collapse;
  margin-top: 20px;
  font-family: 'Poppins', sans-serif;
  width: 45vw;
  margin-right: 20px;
  color: black;
  text-align: center;
}

.pets-table th {
  background-color: #a5b462;
  border: 1px solid #dddddd;
  font-size: 25px;
  text-align: center;
  padding: 8px;
  color: white;
}

.pets-table td {
  border: 1px solid #dddddd;
  padding: 8px;
  background-color: white;
  font-size: 20px;
  white-space: nowrap;
}

.pets-table tr:nth-child(even) {
  background-color: #cac8c8;
}

.pets-table tr:hover {
  background-color: #ddd;
}
</style>