import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },

  petRegister(pet) {
    return axios.post('/registerPet', pet)
  },

  createEvent(playdate, formattedEventTime) {
    return axios.post('/schedule', playdate, formattedEventTime)
  },

  getScheduledPlaydates(eventId){
    return axios.get('/dashboard', eventId)
  },

  getRegisteredPets(petId){
    return axios.get('/pet', petId)
  }
}
