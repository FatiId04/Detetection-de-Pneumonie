import React, { useState, useEffect } from "react";
import "./Hist.css";
import { Link, useLocation } from 'react-router-dom';
import axios from 'axios';

const Hist = () => {
    const [historique, setHistorique] = useState([]);
    const { search } = useLocation();
    const queryParams = new URLSearchParams(search);
    const hospitalName = queryParams.get('hospitalName') || '';

    useEffect(() => {
        const fetchHistorique = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/histo/history?hospitalName=${hospitalName}`);
                setHistorique(response.data);
            } catch (error) {
                console.error('Error fetching history:', error);
            }
        };

        fetchHistorique();
    }, [hospitalName]);

    return (
        <div className="hist">
            <div className="overlap-group-wrapper">
                <div className="overlap-group">
                    <Link to="/" className="text-wrapper">
                        Deconnexion
                    </Link>
                    <h2 className="text-wrapper-1">Historique</h2>
                </div>  
            </div>
            <ul>
                {historique.map((patient, index) => (
                    <li key={index}>
                        <p>Nom: {patient.firstName}</p>
                        <p>Prenom: {patient.lastName}</p>
                        <p>Numéro de dossier: {patient.numeroDossier}</p>
                        <p>Resultat: {patient.result}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default Hist;

