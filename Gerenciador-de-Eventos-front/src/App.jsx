import { BrowserRouter as Router } from "react-router-dom";
import { AuthProvider } from "./contexts/AuthProvider"; // Atualizado
import AppRoutes from "./routes/AppRoutes";

const App = () => {
  return (
    <AuthProvider>
      <Router>
        <AppRoutes />
      </Router>
    </AuthProvider>
  );
};

export default App;
