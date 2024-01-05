
import React, { useState } from "react";
import { Link, useNavigate} from "react-router-dom";
import "./Log.css";

const Log = () => {
  const navigate = useNavigate();

  const [hospitalName, setHospitalName] = useState("");
  
  const [password, setPassword] = useState("");

  const handleLogin = () => {
    // Effectuer la requête HTTP vers l'API Spring Boot
    fetch("http://localhost:8080/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        hospitalName: hospitalName,
        
        password: password,
      }),
    })
    .then((response) => {
      if (response.ok) {
        return response.text(); // Récupérer le texte de la réponse
      } else {
        // Afficher un message d'erreur ou effectuer une action appropriée
        
        throw new Error("Erreur lors de la connexion");
      }
    })
    .then((data) => {
      // Vérifier si la réponse est "found"
      if (data.trim().toLowerCase() === "found") {
        // Rediriger seulement si la réponse est "found"
        navigate({
          pathname: '/Home1',
          search: `?hospitalName=${encodeURIComponent(hospitalName || '')}`,
        });
      } else {
        // Afficher un message d'erreur ou effectuer une action appropriée
        alert("Les données de connexion sont incorrectes.");
      }
    })
    .catch((error) => {
      console.error("Erreur lors de la requête HTTP:", error);
    });
  };

  return (
    <div className="log">
      <div className="overlap-group-wrapper">
        <div className="overlap-group">
          <div className="overlap">
            <div className="div-wrapper">
              <input
                type="text"
                className="text-wrapper"
                placeholder="Nom de l'établissement"
                value={hospitalName}
                onChange={(e) => setHospitalName(e.target.value)}
                required
              />
            </div>
            <div className="div">
              <button onClick={handleLogin} className="con">
                Se connecter
              </button>
            </div>
            
            </div>
            
              <input
                type="password"  
                className="text-wrapper-4"
                placeholder="Mot de passe"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
            
            <div className="rectangle" />
            <div className="text-wrapper-5">se souvenir de moi</div>
          </div>
          <div className="text-wrapper-6">Bienvenue</div>
        </div>
      </div>
    
  );
};

export default Log;
