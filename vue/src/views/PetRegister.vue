<template>
  <div class="pet-register">
    <pet-register-form />
  </div>
</template>
<script>
import PetRegisterForm from "../components/PetRegisterForm.vue";

export default {
  name: "Pet-Register",
  components: {
    PetRegisterForm
  }
}

</script>