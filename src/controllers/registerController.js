import {getMac} from "../../FunctionsAux/functionsAux.js";

const index = (req, res) => {
    res.render('register');
};

const register = async (req, res) => {
    try {
        const data = req.body;

        /**
         * Cria o cadastro do usuário
         * @param {Object} data = Dados do usuário a ser registrado
         * @returns {boolean} true/false = Indicando se o cadastro foi realizado
         */

        const response = await fetch(`http://${getMac()}:8081/api/register`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(res => res.json());
        
        if (response) {
            // Envia os dados usados para verificar no back end;
            const dataLogin = {
                identifier: data.cpf,
                password: data.password
            }

            /**
             * Faz o login do usuário assim que ele se cadastra
             * @param {string} identifier = identificador do usuário cpf/email.
             * @param {string} password = password do usuário.
             * @returns {Promise<Object>} objeto do usuário encontrado.
             */

            const login = await fetch(`http://${getMac()}:8081/api/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(dataLogin)
            }).then(response => response.json());
            
            if (!login) {
                req.flash('error', 'Houve algum erro no login')
                req.session.save(() => {
                  return res.redirect(req.get('Referrer'));
                })
            }
            
            req.session.user = login;
            req.session.save(() => {
                return res.redirect('/');
            })
        }
    } catch (error) {
        console.error(error)
    }
}

export default {
    index,
    register
}