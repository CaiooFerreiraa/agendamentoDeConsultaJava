import { AppointmentModel } from "../models/createDoctor.js";
import { getMac } from "../../FunctionsAux/functionsAux.js";

const index = (req, res) => {
    res.render('make-appointment');
}

const myAppointment = async (req, res) => {
    try {
        const {password, ...data} = req.session.user

        const appointments = await fetch(`http://${getMac()}:8081/api/recovery`, {
            method: 'post',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })

        const consultas = await appointments.json()

        console.log(consultas);
        

        return req.session.save(
            res.render('myAppointment', { consultas })
        )
    } catch (e) {
      console.error(e)
      res.render('error')
    }
}

const marked = async (req, res) => {
    try {
        const {typeSelect, ...dat} = req.body
        const {cpf,name, email, age, ...rest} = req.session.user

        console.log(dat.time);
        

        const data = {
            ...dat,
            name,
            email,
            age,
            cpf
        }

        console.log(data);
        
        //                                           http://192.168.0.100:8000
        const response = await fetch(`http://${getMac()}:8081/api/registrarconsulta`, {
            method: "post",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        }).then(response => response);

        if (!response.ok) {
            req.flash("error", "Houve algum erro ao realizar a consulta, tente novamente");
            req.session.save(() => {
                return res.redirect(req.get("Referrer"))
            })
        } 
        
        req.session.save(() => {
            return res.redirect(req.get("Referrer"))
        });
    }catch (e) {
        console.error(e)
        return res.redirect('/error')
    }
}

const peekHoursTheDoctor = async (req, res) => {
    try {
        const cmDoctor = req.body;

        const response = await fetch(`http://${getMac()}:8081/api/peekHours`, {
            method: 'post',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cmDoctor)
        })

        const test = await response.json();
        
        res.json(test)
    } catch (e) {
        console.error(e)
    }
}

const selectSpecialty = async (req, res) => {
    try {
        const specialty = req.body

        const response = await fetch(`http://${getMac()}:8081/api/specialty`, {
            method: 'post',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(specialty)
        })

        const data = await response.json();
        
        res.json(data);
    } catch (error) {
        console.error(error);
    }
}

const allDoctors = async (req, res) => {
    try {
        const response = await fetch(`http://${getMac()}:8081/api/allDoctors`)

        const doctors = await response.json()

        console.log(doctors);
        
        res.json(doctors)
    } catch (error) {
        console.error(error);
    }

}

const cancelar = async (req, res) => {
    try {
        const {cpf, ...rest} = req.session.user;
        const time = req.body

        const data = {
            cpf,
            time
        }

        console.log(data);
        

        const resp = await fetch(`http://${getMac()}:8081/api/cancelar`, {
            method: 'post',
            headers: {
                'Content-Type': "application/json"
            },
            body: JSON.stringify(data)
        })

        const resposta = await resp.json()

        console.log(resposta, 'oi'
        );

        res.json(resposta)
    } catch (error) {
        
    }
}

export default {
    index,
    marked,
    myAppointment,
    peekHoursTheDoctor,
    selectSpecialty,
    allDoctors,
    cancelar
}