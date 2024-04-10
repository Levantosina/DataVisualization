import React, { useEffect, useState } from 'react';
import ApexCharts from 'apexcharts';
import { getMeasurements } from './services/client.js';
import { errorNotification } from "./services/notification.js"; // Importing the API function

const ChartComponent = () => {
    const [measurements, setMeasurements] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const fetchMeasurements = async () => {
        setLoading(true);
        try {
            const res = await getMeasurements();
            setMeasurements(res.data);
        } catch (err) {
            setError(err.response.data.message);
            errorNotification(err.code);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchMeasurements();
    }, []);

    useEffect(() => {
        let currentChart = null;
        let voltageChart = null;

        if (measurements.length > 0) {
            const currentOptions = {
                chart: {
                    type: 'line',
                    height: 500
                },
                title: {
                    text: "Current measurements",
                    align: 'center'
                },
                series: [{
                    name: 'Current',
                    data: measurements.map(measurement => measurement.current),
                    color: '#ff7f0e',

                }],
                xaxis: {
                    categories: measurements.map(measurement => measurement.timestamp)
                },

            };

            currentChart = new ApexCharts(document.querySelector("#current-chart"), currentOptions);
            currentChart.render();

            const voltageOptions = {
                chart: {
                    type: 'line',
                    height: 500
                },

                title: {
                    text: "Voltage measurements",
                    align: 'center'
                },
                series: [{
                    name: 'Voltage',
                    data: measurements.map(measurement => measurement.voltage),
                    color: '#1f77b4'
                }],
                xaxis: {
                    categories: measurements.map(measurement => measurement.timestamp)
                },

            };

            voltageChart = new ApexCharts(document.querySelector("#voltage-chart"), voltageOptions);
            voltageChart.render();
        }

        // Cleanup function
        return () => {
            if (currentChart) {
                currentChart.destroy();
            }
            if (voltageChart) {
                voltageChart.destroy();
            }
        };
    }, [measurements]);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div>
            <div id="current-chart" style={{ width: '80%', height: '400px' }}></div>
            <div id="voltage-chart" style={{ width: '80%', height: '400px' }}></div>
        </div>
    );
}

export default ChartComponent;
