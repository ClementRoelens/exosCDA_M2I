import ReactDOM from 'react-dom/client'
import './index.css'
import { store } from './config/store.ts'
import { Provider } from 'react-redux'
import { router } from './config/app-routing.tsx'
import { RouterProvider } from 'react-router-dom'

ReactDOM.createRoot(document.getElementById('root')!).render(
  <Provider store={store}>
    <RouterProvider router={router}/>
  </Provider>
)
