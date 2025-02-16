import {getMac} from "../../FunctionsAux/functionsAux.js";

const index = (req, res) => {
  res.render('login.ejs')
}

const previous = (req, res) => {
  res.redirect('/');
}

const submit = async (req, res) => {
  const data = req.body;
  //const csrfToken = res.locals.csrfToken;

  try {
    /**
     * @param {string} identifier = identificador do usuário cpf/email.
     * @param {string} password = password do usuário.
     * @returns {Promise<Object>} objeto do usuário encontrado.
     */
    const response = await fetch(`http://${getMac()}:8081/api/login`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
        // 'x-csrf-token': csrfToken
      },
      body: JSON.stringify(data)
    }).then(response => response.json());

    if (!response) {
      req.flash('error', 'Houve algum erro no login')
      req.session.save(() => {
        return res.redirect(req.get('Referrer'));
      })
    }

    req.session.user = response;
    req.flash('success', 'Login feito com sucesso');
    req.session.save(() => {
      return res.redirect(req.get('Referrer'));
    })
  } catch (e) {
    console.error(e);
    res.redirect('error');
  }
}

export default {
  index,
  previous,
  submit
}