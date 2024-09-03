
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { useKeycloak } from '@react-keycloak/web';
import Header from './components/Header';
import HomePage from './page/Homepage';


function App() {

  return (
    <Router >
      <div className="min-h-screen bg-gray-100"> 
      <main className="container mx-auto px-36 ">
        <Header />
        <Routes>
        <Route path="/" element={<HomePage />} />
      </Routes>
      </main>
      </div>
    </Router>
  );
}

export default App;