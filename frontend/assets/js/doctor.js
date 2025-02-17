const selectDoctor = document.getElementById('doctor');
const timeSlotsSelect = document.getElementById('timeSlots');
const radioTypeSelect = document.getElementsByName("typeSelect");
const specialty = document.getElementById("specialty");

// Atualizar horários quando o médico mudar
selectDoctor.addEventListener('change', async () => {
    timeSlotsSelect.innerHTML = '<option value="" selected>Escolha o horário</option>';

    const data = {
        indenfier: selectDoctor.value
    };

    try {
        const response = await fetch('/peek', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        const timeSlots = await response.json();

        if (Array.isArray(timeSlots)) {
            timeSlots.forEach(slot => {
                const option = document.createElement('option');
                option.value = slot; // O backend deve fornecer o valor no formato `YYYY-MM-DDTHH:mm:ss`
                option.textContent = new Date(slot).toLocaleString(); // Formato legível para o usuário
                timeSlotsSelect.appendChild(option);
            });
        } else {
            console.error('Horários inválidos:', timeSlots);
        }
    } catch (error) {
        console.error('Erro ao buscar horários:', error);
    }
});

radioTypeSelect.forEach((element) => {
    element.addEventListener("change", async () => {
        const selectSpecialty = document.querySelector("#selectSpecialty")

        if (element.id === "especialidade") 
            selectSpecialty.style.display = "block"
        else {
            selectSpecialty.style.display = "none"

            const res = await fetch('/allDoctors')

            const doctos = await res.json()

            updateDoctorSelect(doctos)
        } 
            
        
    })
})


specialty.addEventListener("change", async () => {

    const data = {
        indenfier: specialty.value
    }
    
    
    /**
     * @param {string} valueSpecialty = É a especialidade que o usuário indica filtrar
     * @returns {Promise<Array>} = Array com todos os medicos
     */

    try {
        const res = await fetch("/selectSpecialty", {
            method: "post",
            headers: {
               'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })

        const doctos = await res.json();

        updateDoctorSelect(doctos)
    } catch (error) {
        
    }
})

function updateDoctorSelect(doctors) {
    const doctorSelect = document.getElementById("doctor");

    // Limpa todas as opções
    doctorSelect.innerHTML = "";

    // Adiciona a opção padrão
    const defaultOption = document.createElement("option");
    defaultOption.value = "";
    defaultOption.textContent = "Escolha o seu médico";
    doctorSelect.appendChild(defaultOption);

    // Adiciona os médicos do array
    doctors.forEach(doctor => {
        const option = document.createElement("option");
        option.value = doctor.cm; // Exemplo de value: "marcos-lima"
        option.textContent = doctor.name;
        doctorSelect.appendChild(option);
    });
}

document.addEventListener("click", async (e) => {
    if (e.target.innerHTML == "Cancelar Consulta") {
        const test = document.getElementsByName("hour")[0];
        e.target.style.display = "none"

        const resp = await fetch("/cancelar", {
            method: 'post',
            headers: {
                'Content-Type': "application/json"
            },
            body: JSON.stringify(test.id)
        })

        const respos = await resp.json();
    }
})
