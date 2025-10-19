import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

const CounsellorProfile = () => {
    const { id } = useParams();
    const [counsellor, setCounsellor] = useState(null);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchCounsellor = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/counsellor/${id}`, {
                    credentials: 'include',
                });
                const data = await response.json();
                if (response.ok) {
                    setCounsellor(data.data);
                } else {
                    setError(data.message || 'Failed to fetch counsellor details');
                }
            } catch (err) {
                setError('Failed to connect to the server.');
            }
        };
        fetchCounsellor();
    }, [id]);

    const handleBook = async (sessionTime) => {
        setError('');
        try {
            const response = await fetch('http://localhost:8080/api/counselling/book', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                credentials: 'include',
                body: JSON.stringify({ counsellorId: id, sessionTime }),
            });
            const data = await response.json();
            if (response.ok) {
                alert(data.message);
                navigate('/my-sessions');
            } else {
                setError(data.message || 'Failed to book session');
            }
        } catch (err) {
            setError('Failed to connect to the server.');
        }
    };

    if (error) {
        return <p className="text-red-500">{error}</p>;
    }

    if (!counsellor) {
        return <p>Loading...</p>;
    }

    return (
        <div className="container mx-auto p-4">
            <h1 className="text-3xl font-bold">{counsellor.firstName} {counsellor.lastName}</h1>
            <p className="text-xl text-gray-600">{counsellor.specialization}</p>
            <p className="mt-4">{counsellor.selfDescription}</p>
            <div className="mt-6">
                <h2 className="text-2xl font-bold">Available Time Slots</h2>
                <div className="flex flex-wrap gap-2 mt-2">
                    {counsellor.availableTimes.map((time) => (
                        <button
                            key={time}
                            onClick={() => handleBook(time)}
                            className="bg-green-500 text-white py-2 px-4 rounded hover:bg-green-600"
                        >
                            {time}
                        </button>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default CounsellorProfile;