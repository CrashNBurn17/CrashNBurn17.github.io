<template>
  <div id="app" >
    <div id="nav" :class="{'fixed-nav': isHome}">
      <router-link :to="{ name: 'home' }" class="nav-link">
        <img class="nav-image" src="@/assets/pupmeetup.png" alt="Home" />
      </router-link>
      <div class="links-right">
        <router-link class="nav-link" v-if="$store.state.token" v-bind:to="{ name: 'dashboard' }">Dashboard</router-link>
        <router-link class="nav-link" v-if="$store.state.token" v-bind:to="{ name: 'schedule-playdate' }">Schedule Play Date</router-link>
        <router-link class="nav-link" v-if="$store.state.token" v-bind:to="{ name: 'pet-register' }">Pet Register</router-link>
        <router-link class="nav-link" v-if="$store.state.token" v-bind:to="{ name: 'logout' }">Logout</router-link>
        <router-link class="nav-link" v-bind:to="{ name: 'login' }" v-else>
          <img class="icon-image" src="@/assets/loginicon.png" alt="Home" />
        </router-link>
      </div>
    </div>
    <router-view />
  </div>
  
</template>

<script>
export default {
  computed: {
    isHome() {
      return this.$route.name === 'home';
    },
  },
};
</script>

<style scoped>
html, body {
  margin: 0;
  padding: 0;
}

.fixed-nav {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  display: flex;
  align-items: center;
  height: 150px; 
  width: 100%;
  background-color: #f2f0ee;
  z-index: 1000; 
}

.nav-link {
  display: inline-block;
  padding: 0 16px;
  color: #705a56;
  text-decoration: none;
  font-family: "Poppins", sans-serif;
  font-size: 24px;
  margin-right: 16px;
}

.nav-image {
  height: 100px; 
  max-height: 100%; 
  max-width: 100%; 
  margin: 0 16px;
}

.icon-image {
  height: 55px; 
  max-height: 100%; 
  max-width: 100%; 
  margin: 0 16px;
}

.links-right {
  display: flex;
  align-items: center;
}

#nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

#app {
  background-color: #f2f0ee;
  min-height: 100vh;
  /* display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center; */
}
</style>