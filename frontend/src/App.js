import { Title } from './components/title/Title'
import { Input } from './components/input/Input'
import { Box } from './components/box/Box'
import { useState, useEffect } from 'react'
import axios from 'axios'
import './App.css';

function App() {
  const [session, handleSession] = useState('')

  const getCookie = (cookie) => {
    const cookieList = document.cookie.split(";")
    for (let i = 0; i < cookieList.length; i++) {
      let c = cookieList[i].trim()
      if (c.startsWith(cookie + '='))
        return c.substring(cookie.length + 1)
    }

    return ''
  }

  useEffect(() => {
    const oldToken = getCookie("token")
    if (oldToken.length > 0) {
      axios({
        method: 'delete',
        url: `http://${process.env.REACT_APP_BACKEND}:4567/token/${oldToken}`
      })
    }

    axios({
      method: 'get',
      url: `http://${process.env.REACT_APP_BACKEND}:4567/token`
    })
    .then((res) => res.data)
    .then((json) => {
      const token = json.sessionToken
      document.cookie = `token=${token}`
      return token
    })
    .then(handleSession)
  }, [])

  return (
    <div className="App">
      <Title/>
      <Box sessionToken={session}/>
    </div>
  );
}

export default App;
