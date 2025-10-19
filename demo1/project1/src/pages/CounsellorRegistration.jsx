import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const CounsellorRegistration = () => {
    const [specialization, setSpecialization] = useState('Academics');
    const [selfDescription, setSelfDescription] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        try {
            const response = await fetch('http://localhost:8080/api/counsellor/register', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                credentials: 'include',
                body: JSON.stringify({ specialization, selfDescription }),
            });

            const data = await response.json();

            if (response.ok) {
                alert(data.message);
                navigate('/counsellor-dashboard');
            } else {
                setError(data.message || 'An error occurred');
            }
        } catch (err) {
            setError('Failed to connect to the server.');
        }
    };

    return (
        <div className="container mx-auto p-4">
            <h1 className="text-3xl font-bold mb-4">Register as a Counsellor</h1>
            {error && <p className="text-red-500">{error}</p>}
            <form onSubmit={handleSubmit}>
                <div className="mb-4">
                    <label htmlFor="specialization" className="block text-gray-700 text-sm font-bold mb-2">
                        Specialization
                    </label>
                    <select
                        id="specialization"
                        value={specialization}
                        onChange={(e) => setSpecialization(e.target.value)}
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    >
                        <option>Academics</option>
                        <option>Substance_Addiction</option>
                        <option>Stress_Anxiety</option>
                        <option>Grief_Loss</option>
                        <option>Personal_Relationships</option>
                    </select>
                </div>
                <div className="mb-6">
                    <label htmlFor="selfDescription" className="block text-gray-700 text-sm font-bold mb-2">
                        Self Description
                    </label>
                    <textarea
                        id="selfDescription"
                        value={selfDescription}
                        onChange={(e) => setSelfDescription(e.target.value)}
                        className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                        rows="4"
                    ></textarea>
                </div>
                <div className="flex items-center justify-between">
                    <button
                        type="submit"
                        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                    >
                        Register
                    </button>
                </div>
            </form>
        </div>
    );
};

export default CounsellorRegistration;