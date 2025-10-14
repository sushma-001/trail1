import React from 'react';
import { useNavigate } from 'react-router-dom';

const MentalWellness = () => {
    const navigate = useNavigate();

    const handleRegisterAsCounsellor = () => {
        navigate('/counsellor-registration');
    };

    const handleTakeCounselling = () => {
        navigate('/find-counsellor');
    };

    return (
        <div className="flex items-center justify-center h-screen bg-gray-100">
            <div className="p-8 bg-white rounded-lg shadow-md text-center max-w-md w-full">
                <h2 className="text-2xl font-bold mb-4">Mental Wellness Service</h2>
                <p className="mb-6">Your mental health is a priority. Connect with counsellors or offer your support to others.</p>
                <div className="flex justify-around">
                    <button
                        onClick={handleRegisterAsCounsellor}
                        className="px-6 py-2 text-white bg-blue-600 rounded-md hover:bg-blue-700"
                    >
                        Register as Counsellor
                    </button>
                    <button
                        onClick={handleTakeCounselling}
                        className="px-6 py-2 text-white bg-blue-600 rounded-md hover:bg-blue-700"
                    >
                        Take Counselling
                    </button>
                </div>
            </div>
        </div>
    );
};

export default MentalWellness;