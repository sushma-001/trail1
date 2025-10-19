import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const FindCounsellor = () => {
    const [counsellors, setCounsellors] = useState([]);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchCounsellors = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/counsellor/all', {
                    credentials: 'include',
                });
                const data = await response.json();
                if (response.ok) {
                    setCounsellors(data.data);
                } else {
                    setError(data.message || 'Failed to fetch counsellors');
                }
            } catch (err) {
                setError('Failed to connect to the server.');
            }
        };
        fetchCounsellors();
    }, []);

    return (
        <div className="container mx-auto p-4">
            <h1 className="text-3xl font-bold mb-4">Find a Counsellor</h1>
            {error && <p className="text-red-500">{error}</p>}
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
                {counsellors.map((c) => (
                    <div key={c.counsellorId} className="border p-4 rounded-lg">
                        <h2 className="text-xl font-bold">{c.firstName} {c.lastName}</h2>
                        <p className="text-gray-600">{c.specialization}</p>
                        <p className="mt-2">{c.selfDescription}</p>
                        <button
                            onClick={() => navigate(`/counsellor/${c.counsellorId}`)}
                            className="mt-4 bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600"
                        >
                            View Profile
                        </button>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default FindCounsellor;