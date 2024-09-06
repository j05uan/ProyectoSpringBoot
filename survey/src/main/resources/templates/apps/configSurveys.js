const API_URL = 'http://localhost:8080/api/surveys';

document.getElementById('createSurveyForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const survey = {
        name: document.getElementById('name').value,
        description: document.getElementById('description').value
    };
    
    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(survey)
        });

        if (response.ok) {
            alert('Encuesta creada con éxito');
            document.getElementById('createSurveyForm').reset();
            fetchSurveys();
        } else {
            throw new Error('Error al crear la encuesta');
        }
    } catch (error) {
        alert(error.message);
    }
});

document.getElementById('updateSurveyForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = document.getElementById('updateIdS').value;
    const name = document.getElementById('updateName').value;
    const description = document.getElementById('updateDescription').value;
    
    const response = await fetch(`${API_URL}/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id, name, description })
    });

    if (response.ok) {
        alert('Encuesta actualizada con éxito');
        document.getElementById('createSurveyForm').reset();
        fetchSurveys();
    } else {
        alert('Error al actualizar la encuesta');
    }
});

document.getElementById('deleteSurveyForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = document.getElementById('deleteId').value;
    
    const response = await fetch(`${API_URL}/${id}`, {
        method: 'DELETE'
    });

    if (response.ok) {
        alert('Encuesta eliminada con éxito');
        document.getElementById('createSurveyForm').reset();
        fetchSurveys();
    } else {
        alert('Error al eliminar la encuesta');
    }
});

async function fetchSurveys() {
    const response = await fetch(API_URL);
    const surveys = await response.json();
    
    const surveyList = document.getElementById('surveyList');
    surveyList.innerHTML = '';
    
    surveys.forEach(survey => {
        const li = document.createElement('li');
        li.textContent = `ID: ${survey.id}, Nombre: ${survey.name}, Descripción: ${survey.description}`;
        surveyList.appendChild(li);
    });
}
async function fetchSurveysIn(id) {
    try {
        const response = await fetch('http://localhost:8080/api/surveys');
        const surveys = await response.json();
        
        const surveySelect = document.getElementById(id);
        surveySelect.innerHTML = '<option value="">Selecciona una encuesta</option>'; // Limpiar opciones actuales
        
        surveys.forEach(survey => {
            const option = document.createElement('option');
            option.value = survey.id;
            option.textContent = `${survey.name} - ${survey.description}`;
            surveySelect.appendChild(option);
        });
    } catch (error) {
        console.error('Error al obtener encuestas:', error);
    }
}
// Cargar encuestas al inicio
document.addEventListener('DOMContentLoaded', () => {
    fetchSurveys();
    fetchSurveysIn('surveyIdS');
    fetchSurveysIn('updateIdS');
    fetchSurveysIn('deleteId');
    fetchSurveysIn('updateSurveyId');
    fetchSurveysIn('questionSurveyId');


});