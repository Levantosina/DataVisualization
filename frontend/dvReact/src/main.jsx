import React from 'react';
import ReactDOM from 'react-dom';
import { createRoot } from 'react-dom/client';
import './index.css';
import ChartComponent from './ChartComponent.jsx';

createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <ChartComponent />
    </React.StrictMode>
);
