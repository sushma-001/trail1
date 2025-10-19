import React from 'react';
import { useNavigate } from 'react-router-dom';

const MentalWellness = () => {
    const navigate = useNavigate();

    return (
        <div className="container mx-auto p-4">
            <h1 className="text-3xl font-bold mb-4">Mental Wellness</h1>
            <div className="flex space-x-4">
                <button
                    onClick={() => navigate('/counsellor-registration')}
                    className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                >
                    Register as Counsellor
                </button>
                <button
                    onClick={() => navigate('/find-counsellor')}
                    className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded"
                >
                    Take Counselling
                </button>
            </div>
        </div>
    );
};

export default MentalWellness;