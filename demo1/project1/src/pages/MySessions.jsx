import React, { useState, useEffect } from 'react';
import axios from 'axios';

const MySessions = () => {
    const [sessions, setSessions] = useState([]);
    const [error, setError] = useState('');
    const studentId = sessionStorage.getItem('studentId');

    useEffect(() => {
        const fetchSessions = async () => {
            if (!studentId) return;
            try {
                const response = await axios.get(`/api/counselling/my-sessions/${studentId}`);
                setSessions(response.data.data);
            } catch (err) {
                setError('Failed to fetch sessions.');
            }
        };

        fetchSessions();
    }, [studentId]);

    const handleRateSession = async (counsellorId, rating, feedback) => {
        try {
            await axios.post('/api/counselling/rate', {
                studentId: parseInt(studentId),
                counsellorId,
                rating,
                feedback,
            });
            alert('Rating submitted successfully!');
        } catch (err) {
            setError(err.response?.data?.message || 'Failed to submit rating.');
        }
    };

    return (
        <div className="p-8">
            <h2 className="text-2xl font-bold mb-4">My Sessions</h2>
            {error && <p className="text-red-500 mb-4">{error}</p>}
            <div className="space-y-4">
                {sessions.map((session) => (
                    <div key={session.finalTime} className="p-4 bg-white rounded-lg shadow-md">
                        <p><strong>Counsellor:</strong> {session.counsellorId}</p>
                        <p><strong>Time:</strong> {new Date(session.finalTime).toLocaleString()}</p>
                        <div className="mt-4">
                            <form onSubmit={(e) => {
                                e.preventDefault();
                                const rating = parseInt(e.target.rating.value);
                                const feedback = e.target.feedback.value;
                                handleRateSession(session.counsellorId, rating, feedback);
                            }}>
                                <div className="flex items-center space-x-2">
                                    <select name="rating" className="px-3 py-2 border rounded-md">
                                        <option value="1">1 Star</option>
                                        <option value="2">2 Stars</option>
                                        <option value="3">3 Stars</option>
                                        <option value="4">4 Stars</option>
                                        <option value="5">5 Stars</option>
                                    </select>
                                    <textarea
                                        name="feedback"
                                        placeholder="Feedback"
                                        className="w-full px-3 py-2 border rounded-md"
                                    ></textarea>
                                    <button
                                        type="submit"
                                        className="px-6 py-2 text-white bg-blue-600 rounded-md hover:bg-blue-700"
                                    >
                                        Rate
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default MySessions;