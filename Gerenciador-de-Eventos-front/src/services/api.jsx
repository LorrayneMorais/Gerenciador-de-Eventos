import axios from "axios";

export const api = axios.create({
  baseURL: "http://localhost:8080", // Altere para o baseURL da sua API
  // headers: {
  //   Authorization: `Basic ${btoa("admin:admin123")}`, // Deixe a autenticação se necessário
  // },
});
