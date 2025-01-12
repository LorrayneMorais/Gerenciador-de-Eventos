// src/components/Header.jsx
import { Link } from "react-router-dom";
import logo from "../assets/logo.png"; // Importe a imagem
import "../styles/Header.css"; // Caminho relativo para o CSS

function Header() {
  return (
    <header className="header">
      <div className="logo">
        <img src={logo} alt="Gestão de Eventos" className="logo-img" />{" "}
        {/* Exibindo a imagem */}
      </div>
      <nav>
        <ul>
          <li>
            <Link to="/">Login</Link>
          </li>
          <li>
            <Link to="/register">Cadastrar-se</Link>
          </li>
        </ul>
      </nav>
    </header>
  );
}

export default Header;
