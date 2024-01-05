import React, { useState } from "react";
import "./Home1.css";
import check from './assets/check.png';
import again from './assets/again.png';
import para from './assets/para.png';
import { Link,useLocation } from 'react-router-dom';
import axios from 'axios';

function Home1() {
    const [nom, setNom] = useState("");
    const [prenom, setPrenom] = useState("");
    const [numeroDossier, setNumeroDossier] = useState("");
    const [selectedFile, setSelectedFile] = useState(null);
    const [previewImage, setPreviewImage] = useState(null);

    const { search } = useLocation();
    const queryParams = new URLSearchParams(search);
    const hospitalName = queryParams.get('hospitalName') || '';


    const handleFileChange = (event) => {
        const file = event.target.files[0];
        setSelectedFile(file);

        // Créer une URL pour prévisualiser l'image
        const imagePreviewUrl = URL.createObjectURL(file);
        setPreviewImage(imagePreviewUrl);
    };

    const handleSave = () => {
        if (nom && prenom && numeroDossier && selectedFile) {
            const formData = new FormData();
            formData.append('nom', nom);
            formData.append('prenom', prenom);
            formData.append('numeroDossier', numeroDossier);
            formData.append('image', selectedFile);
            formData.append('hospitalName', hospitalName);

            axios.post('http://localhost:8080/api/patients/upload', formData)
                .then(response => {
                    console.log('Réponse du backend:', response.data);
                    setNom("");
                    setPrenom("");
                    setNumeroDossier("");
                    setSelectedFile(null);
                    setPreviewImage(null);
                })
                .catch(error => {
                    console.error('Erreur lors de l\'envoi au backend:', error);
                    if (error.response) {
                        console.error('Réponse du serveur:', error.response);
                        // Ajouter cette ligne pour afficher la réponse du serveur dans la console
                        console.log(error.response);
                    } else if (error.request) {
                        console.error('Aucune réponse du serveur reçue');
                    } else {
                        console.error('Erreur lors de la configuration de la requête:', error.message);
                    }
                });
        } else {
            console.log("Veuillez remplir tous les champs");
        }
    };

    const handleReset = () => {
        setNom("");
        setPrenom("");
        setNumeroDossier("");
        setSelectedFile(null);
        setPreviewImage(null);
    };

    return (
        <div className="home">
            <div className="div">
                <div className="overlap">
                    <Link to="/" className="text-wrapper">
                        Deconnexion
                    </Link>
                    <div className="Bien">Bienvenue {hospitalName}</div>
                    <img
                        className="checklist-list-icon"
                        alt="Checklist list icon"
                        src={para}
                    />
                </div>
                <div className="overlap-group">
                <label htmlFor="fileInput" className="text-wrapper-2">
                 Choisir un fichier
                <input type="file" id="fileInput" style={{ display: 'none' }} onChange={handleFileChange} />
                </label>
                </div>
                <div className="three-thin-arrows-wrapper" onClick={handleReset}>
                    <img className="three-thin-arrows" alt="Three thin arrows" src={again} />
                </div>
                <div className="green-check-mark-wrapper" onClick={handleSave}>
                    <img  
                        className="green-check-mark"
                        alt="Green check mark"
                        src={check}
                    />
                </div>
                <div className="overlap-2">
                    {previewImage && <img src={previewImage} alt="Prévisualisation" />}
                </div>
                <div className="overlap-group-2">
                    <div className="div-wrapper">
                        <input type="text" className="p" placeholder="Nom" value={nom} onChange={(e) => setNom(e.target.value)} required />
                    </div>
                    <div className="overlap-3">
                        <input type="text" className="text-wrapper-4" placeholder="Prenom" value={prenom} onChange={(e) => setPrenom(e.target.value)} required />
                    </div>
                    <div className="overlap-4">
                        <input type="text" className="text-wrapper-5" placeholder="Numéro de dossier" value={numeroDossier} onChange={(e) => setNumeroDossier(e.target.value)} required />
                    </div>
                    <Link  to={{ pathname: "/Hist",  search: `?hospitalName=${encodeURIComponent(hospitalName || "")}` }}className="text-wrapper-7">
                    Historique
                   </Link>
                   </div>
            </div>
        </div>
    );
}

export default Home1;
