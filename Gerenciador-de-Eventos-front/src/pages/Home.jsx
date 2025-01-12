import { useState, useEffect, useCallback } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../styles/Home.css";

const Home = () => {
  const [events, setEvents] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [newEvent, setNewEvent] = useState({
    name: "",
    date: "",
    location: "",
    image: "",
  });
  const [error, setError] = useState("");
  const navigate = useNavigate();

  // Função para carregar eventos da API
  // const fetchEvents = useCallback(async () => {
  //   try {
  //     const token = localStorage.getItem("jwtToken");
  //     if (!token) {
  //       navigate("/login"); // Redireciona para login se o token não existir
  //       return;
  //     }

  //     const response = await axios.get("http://localhost:8080/api/events", {
  //       headers: {
  //         Authorization: `Bearer ${token}`,
  //       },
  //     });
  //     setEvents(response.data);
  //   } catch (error) {
  //     console.error("Erro ao carregar eventos:", error);
  //     setError("Não foi possível carregar os eventos.");
  //   }
  // }, [navigate]);

  // Função para adicionar evento via API
  const handleAddEvent = async () => {
    try {
      const token = localStorage.getItem("jwtToken");
      if (!token) {
        navigate("/login"); // Redireciona para login se o token não existir
        return;
      }

      const response = await axios.post(
        "http://localhost:8080/api/events",
        newEvent,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setEvents([...events, response.data]);
      setNewEvent({ name: "", date: "", location: "", image: "" });
      setShowModal(false);
      setError(""); // Limpar erro caso seja bem-sucedido
    } catch (error) {
      console.error("Erro ao adicionar evento:", error);
      setError("Erro ao adicionar o evento.");
    }
  };

  // Função para excluir evento via API
  const handleDeleteEvent = async (eventId) => {
    try {
      const token = localStorage.getItem("jwtToken");
      if (!token) {
        navigate("/login");
        return;
      }

      await axios.delete(`http://localhost:8080/api/events/${eventId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setEvents(events.filter((event) => event.id !== eventId));
    } catch (error) {
      console.error("Erro ao excluir evento:", error);
      setError("Erro ao excluir o evento.");
    }
  };

  // // Carregar eventos quando o componente for montado
  // useEffect(() => {
  //   fetchEvents();
  // }, [fetchEvents]);

  return (
    <div className="home-container">
      <h2>Eventos</h2>
      {error && <p className="error-message">{error}</p>}{" "}
      {/* Exibir mensagem de erro */}
      <button onClick={() => setShowModal(true)}>Adicionar Evento</button>
      <div className="event-list">
        {events.map((event) => (
          <div key={event.id} className="event-item">
            <img src={event.image} alt={event.name} />
            <h3>{event.name}</h3>
            <p>{event.date}</p>
            <p>{event.location}</p>
            <button onClick={() => handleDeleteEvent(event.id)}>Excluir</button>
          </div>
        ))}
      </div>
      {showModal && (
        <div className="modal">
          <h2>Adicionar Evento</h2>
          <input
            className="input-home"
            type="text"
            placeholder="Nome do Evento"
            value={newEvent.name}
            onChange={(e) => setNewEvent({ ...newEvent, name: e.target.value })}
          />
          <input
            className="input-home"
            type="date"
            value={newEvent.date}
            onChange={(e) => setNewEvent({ ...newEvent, date: e.target.value })}
          />
          <input
            className="input-home"
            type="text"
            placeholder="Localização"
            value={newEvent.location}
            onChange={(e) =>
              setNewEvent({ ...newEvent, location: e.target.value })
            }
          />
          <input
            className="input-home"
            type="text"
            placeholder="URL da Imagem"
            value={newEvent.image}
            onChange={(e) =>
              setNewEvent({ ...newEvent, image: e.target.value })
            }
          />
          <button onClick={handleAddEvent}>Salvar</button>
          <button onClick={() => setShowModal(false)}>Cancelar</button>
        </div>
      )}
    </div>
  );
};

export default Home;
