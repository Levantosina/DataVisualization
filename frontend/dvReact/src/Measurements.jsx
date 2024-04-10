import React from 'react';

function Measurements({ current,voltage }) {
    return (
        <div>
            <p>Voltage: {voltage}</p>
            <p>Current: {current}</p>
        </div>
    );
}

export default Measurements;