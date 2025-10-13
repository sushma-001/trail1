import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const CounsellorRegistration = () => {
    const [specialization, setSpecialization] = useState('Academics');
    const [selfDescription, setSelfDescription] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        const studentId = sessionStorage.getItem('studentId');
        if (!studentId) {
            setError('You must be logged in to register as a counsellor.');
            return;
        }

        try {
            await axios.post('/api/counsellor/register', {
                counsellorId: parseInt(studentId),
                specialization,
                selfDescription,
            });
            navigate('/counsellor-dashboard');
        } catch (err) {
            setError(err.response?.data?.message || 'An error occurred during registration.');
        }
    };

    return (
        <div className="flex items-center justify-center h-screen bg-gray-100">
            <div className="p-8 bg-white rounded-lg shadow-md w-full max-w-md">
                <h2 className="text-2xl font-bold mb-4 text-center">Register as a Counsellor</h2>
                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label htmlFor="specialization" className="block text-gray-700">Specialization</label>
                        <select
                            id="specialization"
                            value={specialization}
                            onChange={(e) => setSpecialization(e.target.value)}
                            className="w-full px-3 py-2 border rounded-md"
                        >
                            <option value="Academics">Academics</option>
                            <option value="Substance_Addiction">Substance Addiction</option>
                            <option value="Stress_Anxiety">Stress & Anxiety</option>
                            <option value="Grief_Loss">Grief & Loss</option>
                            <option value="Personal_Relationships">Personal Relationships</option>
                        </select>
                    </div>
                    <div className="mb-4">
                        <label htmlFor="selfDescription" className="block text-gray-700">Self Description</label>
                        <textarea
                            id="selfDescription"
                            value={selfDescription}
                            onChange={(e) => setSelfDescription(e.target.value)}
                            rows="4"
                            className="w-full px-3 py-2 border rounded-md"
                        ></textarea>
                    </div>
                    {error && <p className="text-red-500 text-center mb-4">{error}</p>}
                    <button
                        type="submit"
                        className="w-full px-6 py-2 text-white bg-blue-600 rounded-md hover:bg-blue-700"
                    >
                        Register
                    </button>
                </form>
            </div>
        </div>
    );
};

export default CounsellorRegistration;