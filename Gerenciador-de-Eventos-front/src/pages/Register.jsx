import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Header from "../components/Header"; // Importando o Header
import "../styles/Register.css";
import { api } from "../services/api";

const Register = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmaSenha, setConfirmaSenha] = useState("");
  const [errorMessage, setErrorMessage] = useState(""); // Mensagem de erro
  const [isModalVisible, setIsModalVisible] = useState(false); // Controle de visibilidade do modal
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();
    if (!name || !email || !password || !confirmaSenha) {
      setErrorMessage("Todos os campos são obrigatórios!");
      setIsModalVisible(true);
      return;
    }

    // Validação de senha
    if (password !== confirmaSenha) {
      setErrorMessage("As senhas não coincidem!");
      setIsModalVisible(true);
      return;
    }
    const usuario = {
      nomeAdmin: name,
      email,
      password,
      confirmaSenha,
    };

    try {
      // Enviando os dados para o backend
      const response = await api.post("/api/admin/register", usuario);

      // Verifica a resposta do backend
      if (response.status === 201) {
        alert("Cadastro realizado com sucesso!");
        navigate("/login"); // Redireciona para a página de login
      } else {
        setErrorMessage("Ocorreu um erro ao cadastrar!");
        setIsModalVisible(true);
      }
    } catch (error) {
      // Trata erros de requisição
      setErrorMessage(error.response?.data?.message || "Erro ao cadastrar!");
      setIsModalVisible(true);
    }
  };

  return (
    <div className="register-container">
      <Header /> {/* Inserindo o Header aqui */}
      <h2>Cadastro de Administrador</h2>
      <form onSubmit={handleRegister}>
        <input
          type="text"
          placeholder="Nome"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="password"
          placeholder="Senha"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <input
          type="password"
          placeholder="Confirmar Senha"
          value={confirmaSenha}
          onChange={(e) => setConfirmaSenha(e.target.value)}
        />
        <button type="submit">Cadastrar</button>
      </form>
      {/* Modal para mensagens importantes */}
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

export default Register;
