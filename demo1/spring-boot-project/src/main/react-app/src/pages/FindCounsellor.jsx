import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const FindCounsellor = () => {
    const [counsellors, setCounsellors] = useState([]);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchCounsellors = async () => {
            try {
                const response = await axios.get('/api/counsellor/all');
                setCounsellors(response.data.data);
            } catch (err) {
                setError('Failed to fetch counsellors.');
            }
        };

        fetchCounsellors();
    }, []);

    const handleViewProfile = (counsellorId) => {
        navigate(`/counsellor/${counsellorId}`);
    };

    return (
        <div className="p-8">
            <h2 className="text-2xl font-bold mb-4">Find a Counsellor</h2>
            {error && <p className="text-red-500 mb-4">{error}</p>}
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
                {counsellors.map((counsellor) => (
                    <div key={counsellor.counsellorId} className="p-6 bg-white rounded-lg shadow-md">
                        <h3 className="text-xl font-bold">{counsellor.student.firstName} {counsellor.student.lastName}</h3>
                        <p className="text-gray-600"><strong>Specialization:</strong> {counsellor.specialization}</p>
                        <p className="text-gray-600"><strong>Rating:</strong> {counsellor.rating.toFixed(2)}</p>
                        <p className="mt-2">{counsellor.selfDescription}</p>
                        <button
                            onClick={() => handleViewProfile(counsellor.counsellorId)}
                            className="mt-4 px-4 py-2 text-white bg-blue-600 rounded-md hover:bg-blue-700"
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