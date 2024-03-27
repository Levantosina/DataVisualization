// import React, { useEffect, useState } from 'react';
// import ApexCharts from 'apexcharts';
// import { getMeasurements } from './services/client.js'; // Importing the API function
//
// function ChartComponent() {
//     const [measurements, setMeasurements] = useState([]);
//
//     useEffect(() => {
//         const fetchMeasurements = async () => {
//             try {
//                 const response = await getMeasurements();
//                 setMeasurements(response.data); // Assuming measurements are in the response data
//             } catch (error) {
//                 console.error('Error fetching measurements:', error);
//             }
//         };
//
//         fetchMeasurements();
//     }, []);
//
//     useEffect(() => {
//         let chart = null;
//
//         if (measurements.length > 0) {
//             const options = {
//                 chart: {
//                     type: 'line'
//                 },
//                 series: [{
//                     name: 'Measurements',
//                     data: measurements.map(measurement => measurement.current)
//                 }],
//                 xaxis: {
//                     categories: measurements.map(measurement => measurement.timestamp)
//                 }
//             };
//
//             chart = new ApexCharts(document.querySelector("#chart"), options);
//             chart.render();
//         }
//
//         // Cleanup function
//         return () => {
//             if (chart) {
//                 chart.destroy();
//             }
//         };
//     }, [measurements]);
//
//     return <div id="chart"></div>;
// }
//
// export default ChartComponent;
