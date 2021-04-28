import axios from "axios";

export default axios.create({
  baseURL: "./api", //server-side dev port http://localhost:8081/api
  headers: {
    "Content-type": "application/json"
  }
});