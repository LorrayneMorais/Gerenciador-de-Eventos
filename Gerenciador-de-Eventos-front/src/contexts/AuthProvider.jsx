import { useState, useEffect } from "react";
import PropTypes from "prop-types";
import { AuthContext } from "./AuthContext"; // Importação do contexto

export const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [token, setToken] = useState(null);

  // Verifica se existe um token no localStorage ao carregar o componente
  useEffect(() => {
    const storedToken = localStorage.getItem("jwtToken");
    if (storedToken) {
      setToken(storedToken);
      setIsAuthenticated(true);
    }
  }, []);

  // Função de login
  const login = (newToken) => {
    localStorage.setItem("jwtToken", newToken);
    setToken(newToken);
    setIsAuthenticated(true);
  };

  // Função de logout
  const logout = () => {
    localStorage.removeItem("jwtToken");
    setToken(null);
    setIsAuthenticated(false);
  };

  return (
    <AuthContext.Provider value={{ isAuthenticated, login, logout, token }}>
      {children}
    </AuthContext.Provider>
  );
};

// Validação de `children` com PropTypes
AuthProvider.propTypes = {
  children: PropTypes.node.isRequired,
};
