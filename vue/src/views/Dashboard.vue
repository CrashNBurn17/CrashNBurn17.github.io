<template>
  <div class="dashboard-display">
    <play-date-table /> <pets-table />
  </div>
</template>

<script>
import PetsTable from '../components/PetsTable.vue';
import PlayDateTable from '../components/PlayDateTable.vue';

export default {
  name: "Dashboard-Display",
  components: {
    PlayDateTable,
    PetsTable
  }
}

</script>

<style scoped>
#profile-nav {
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
}
.dashboard-display {
  background-color: #f2f0ee;
  min-height: 100vh;
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
  justify-content: space-evenly;
}
</style>