import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Header from "../components/Header"; // Importando o Header
import "../styles/Login.css";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [rememberMe, setRememberMe] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const [isModalVisible, setIsModalVisible] = useState(false);
  const navigate = useNavigate();

  console.log(email, password);
  const handleLogin = async (e) => {
    e.preventDefault();
    if (!email || !password) {
      setErrorMessage("Por favor, preencha todos os campos.");
      setIsModalVisible(true);
      return;
    }

    try {
      const response = await axios.post(
        "http://localhost:8080/api/api/admin/login",
        {
          email,
          password,
        }
      );

      console.log(response);

      if (response.status === 200) {
        const token = response.data.token;
        console.log(response);
        if (rememberMe) {
          localStorage.setItem("username", JSON.stringify(email));
          localStorage.setItem("jwtToken", token);
          console.log(response);
        } else {
          sessionStorage.setItem("jwtToken", token);
          sessionStorage.setItem("username", JSON.stringify(email));
        }

        axios.defaults.headers.Authorization = `Bearer ${token}`;
        navigate("/home");
        console.log(response);
      } else {
        setErrorMessage("Credenciais inválidas. Tente novamente.");
        setIsModalVisible(true);
        console.log(response);
      }
    } catch (error) {
      console.error(
        "Erro ao fazer login:",
        error.response?.data || error.message
      );
      setErrorMessage("Senha ou email incorretos.");
      setIsModalVisible(true);
      console.log(error);
    }
  };

  return (
    <div className="container">
      <Header /> {/* Adicionando o Header acima do formulário */}
      <div className="imagem"></div>
      <div className="login-container">
        <h2>Login</h2>
        <form className="form" onSubmit={handleLogin}>
          <input
            className="input"
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <input
            className="input"
            type="password"
            placeholder="Senha"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <label>
            <input
              className="input"
              type="checkbox"
              checked={rememberMe}
              onChange={(e) => setRememberMe(e.target.checked)}
            />
            Gravar Senha
          </label>
          <button type="submit">Entrar</button>
          <button type="button" onClick={() => navigate("/register")}>
            Cadastrar-se
          </button>
        </form>
      </div>
      {isModalVisible && (
        <div className="modal">
          <div className="modal-content">
            <p>{errorMessage}</p>
            <button onClick={() => setIsModalVisible(false)}>Fechar</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Login;
