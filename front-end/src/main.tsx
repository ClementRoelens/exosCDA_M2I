import ReactDOM from 'react-dom/client'
import { RouterProvider } from 'react-router-dom'
import { router } from './config/app-routing.tsx'
import { Provider } from 'react-redux'
import { store } from './config/store.ts'

ReactDOM.createRoot(document.getElementById('root')!).render(
  <Provider store={store}>
    <RouterProvider router={router}></RouterProvider>
  </Provider>
)
