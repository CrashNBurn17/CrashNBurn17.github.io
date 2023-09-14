<template>
  <div class="home" >
    
    <div class="content-row">
      <div class="content-column">
        <img src="@/assets/twodogs.jpg" class="dogs-image">
      </div>
      <div class="content-column">
        <h2 class="section">Our Mission</h2>
        <p class="description">We look to create joyful connections between dogs and their loving owners in the Pittsburgh community. We're dedicated to fostering a vibrant pet-friendly community where companionship and play thrive.</p>
        <p class="description">Our platform is designed to make scheduling and organizing play dates for pets a breeze, providing a safe and welcoming space for pet owners to connect, share experiences, and create lasting memories. By promoting active lifestyles and strengthening the bonds between pets and their families, we aim to enrich the lives of both animals and humans alike. </p>
        <p class="description">Join us in building a paw-sitive and interconnected world, one playful encounter at a time.</p>
      </div>
    </div>

    <div class="content-row">
      <div class="content-column">
        <h2 class="section">How It Works: Play Dates Made Simple</h2>
        <p class="description">1 - Craft a personalized profile for your pup, highlighting breed and personality traits.</p>
        <p class="description">2 - Select from a variety of play date meetups in your local area.</p>
        <p class="description">3 - Create play date events for others to join or discover existing ones.</p>
        <p class="description">4 - Connect with fellow dog lovers, swap stories, and capture play date memories.</p>
        <p class="description">5 - Forge bonds in Pittsburgh's pet-loving community.</p>
      </div>
      <div class="content-column">
        <img src="@/assets/doggies.webp" class="dogs-image">
      </div>
    </div>

    <div class="content-row">
      <div class="content-column">
        <img src="@/assets/pittsburghparks.png" class="dogs-image">
      </div>
      <div class="content-column">
        <h2 class="section">Dog Park Discovery Map</h2>
        <p class="description">Navigate through Pittsburgh's diverse neighborhoods and uncover a network of meticulously curated dog parks, each with its own unique charm and amenities.</p>
        <p class="description">From lush open spaces to agility courses that challenge even the most spirited pups, our map showcases a plethora of options to cater to your dog's play preferences.</p>
      </div>
    </div>
  </div>
</template>
<script>

export default {
  name: "Home",

};
</script>

<style scoped>
.home {
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding-top: 550px;
  height: 100vh;
  padding: 550px 20px;
}

.content-row {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 100px 0 1rem;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);  
  border-radius: 10px;
}

.content-column {
  font-family: 'Poppins', sans-serif;
  flex: 1;
  padding: 0 1rem;
  text-align: center;
}

.dogs-image {
  height: 80%;
  width: 80%;
  border-radius: 10px;
}

.section {
font-size: 35px;
color: #551E19
}

.description {
  text-align: left;
  font-size: 24px;
  color: #705a56;
}

</style>

