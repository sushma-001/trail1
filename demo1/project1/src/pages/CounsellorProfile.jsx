import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

const CounsellorProfile = () => {
    const { id } = useParams();
    const [counsellor, setCounsellor] = useState(null);
    const [error, setError] = useState('');

    const studentId = sessionStorage.getItem('studentId');

    useEffect(() => {
        const fetchCounsellor = async () => {
            try {
                const response = await axios.get(`/api/counsellor/${id}`);
                setCounsellor(response.data.data);
            } catch (err) {
                setError('Failed to fetch counsellor details.');
            }
        };

        fetchCounsellor();
    }, [id]);

    const handleBookSession = async (time) => {
        if (!studentId) {
            setError('You must be logged in to book a session.');
            return;
        }
        try {
            await axios.post('/api/counselling/book', {
                studentId: parseInt(studentId),
                counsellorId: parseInt(id),
                sessionTime: time,
            });
            alert('✅ Appointment approved');
        } catch (err) {
            setError(err.response?.data?.message || 'Failed to book session.');
        }
    };

    if (error) {
        return <p className="text-red-500 text-center">{error}</p>;
    }

    if (!counsellor) {
        return <p className="text-center">Loading...</p>;
    }

    return (
        <div className="p-8 flex justify-center">
            <div className="p-6 bg-white rounded-lg shadow-md max-w-lg w-full">
                <h2 className="text-2xl font-bold">{counsellor.student.firstName} {counsellor.student.lastName}</h2>
                <p className="text-gray-600"><strong>Specialization:</strong> {counsellor.specialization}</p>
                <p className="text-gray-600"><strong>Rating:</strong> {counsellor.rating.toFixed(2)}</p>
                <p className="mt-4">{counsellor.selfDescription}</p>

                <div className="mt-6">
                    <h3 className="text-xl font-bold mb-2">Available Times</h3>
                    <ul>
                        {counsellor.availableTimes && counsellor.availableTimes.filter(t => !t.booked).map((time) => (
                            <li key={time.availableTime} className="flex justify-between items-center py-2 border-b">
                                <span>{time.availableTime}</span>
                                <button
                                    onClick={() => handleBookSession(time.availableTime)}
                                    className="px-4 py-1 text-white bg-blue-600 rounded-md hover:bg-blue-700"
                                >
                                    Book
                                </button>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        </div>
    );
};

export default CounsellorProfile;