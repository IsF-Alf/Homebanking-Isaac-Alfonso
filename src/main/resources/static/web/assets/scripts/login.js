const { createApp } = Vue;

createApp({
  data() {
    return {
      email: "",
      password: "",
      firstName: "",
      lastName: "",
      emailRegister: "",
      passwordRegister: "",
      msjError:"",
      showRegisterForm: false,
    };
  },
  created() {},
  methods: {
    loginClient() {
      const clientLogin = `email=${this.email}&password=${this.password}`;
      axios
        .post("/api/login", clientLogin)
        .then((response) => {
          console.log("signed in!!!");
          location.pathname = "/web/accounts.html";
        })
        .catch((error) => alert("Wrong username or password"));
    },
    logoutClient() {
      axios.post("/api/logout").then((response) => {
        console.log("signed out!!!");
        location.href = "http://localhost:8080";
      });
    },
    registerClient() {
      const clientsData = `firstName=${this.firstName}&lastName=${this.lastName}&email=${this.emailRegister}&password=${this.passwordRegister}`;
      axios
        .post("/api/clients", clientsData)
        .then((response) => {
          const clientLogin = `email=${this.emailRegister}&password=${this.passwordRegister}`;
          axios.post("/api/login", clientLogin)
          .then((response) => {
            location.pathname = "/web/accounts.html";
          })
        })
        .catch((error) =>
        {error.response.data = this.msjError 
          console.log(this.msjError)} );
    },
    toggleRegisterForm() {
      this.showRegisterForm = !this.showRegisterForm;
    },
  },
}).mount("#app");
