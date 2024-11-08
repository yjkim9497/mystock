import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import Homepage from './pages/Homepage'
import LoginPage from './pages/LoginPage'
import { QueryClient, QueryClientProvider } from 'react-query'
import SignUpPage from './pages/SignUpPage'

function App() {
  const queryClient = new QueryClient();
  return (
    <>
      <QueryClientProvider client={queryClient}>
        <BrowserRouter>
          <div>
            <main>
              <Routes>
                <Route path="/" element={<Homepage />}></Route>
                <Route path="/login" element={<LoginPage />}></Route>
                <Route path="/signup" element={<SignUpPage />}></Route>
              </Routes>
            </main>
          </div>
        </BrowserRouter>

      </QueryClientProvider>
    </>
  )
}

export default App
