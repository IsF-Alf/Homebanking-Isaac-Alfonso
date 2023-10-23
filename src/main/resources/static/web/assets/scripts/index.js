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
        .catch((error) => console.log(error));
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
          alert("Creted new Client");
          console.log(response);
          location.pathname = "/index.html";
        })
        .catch((error) => console.log(error));
    },
  },
}).mount("#app");
