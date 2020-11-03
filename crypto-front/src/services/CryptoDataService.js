import http from "../http-common";

class CryptoDataService {
  getAll() {
    return http.get("/cryptos");
  }

  get(id) {
    return http.get(`/cryptos/${id}`);
  }

  create(data) {
    return http.post("/cryptos", data);
  }

  update(id, data) {
    return http.put(`/cryptos/${id}`, data);
  }

  delete(id) {
    return http.delete(`/cryptos/${id}`);
  }

  deleteAll() {
    return http.delete(`/cryptos`);
  }

}

export default new CryptoDataService();