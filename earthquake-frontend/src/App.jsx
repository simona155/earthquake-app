import { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";

function App() {
  const [earthquakes, setEarthquakes] = useState([]);
  const [loading, setLoading] = useState(true);

  const fetchData = async () => {
    setLoading(true);
    try {
      const res = await fetch("http://localhost:8080/api/earthquakes", {
        cache: "no-store",
      });

      const data = await res.json();
      setEarthquakes(data);
    } catch (error) {
      console.error("Error fetching data:", error);
    } finally {
      setLoading(false);
    }
  };

  const fetchNewData = async () => {
    setLoading(true);
    await fetch("http://localhost:8080/api/earthquakes/fetch", {
      cache: "no-store",
    });
    await fetchData();
  };

  const deleteEarthquake = async (id) => {
    await fetch(`http://localhost:8080/api/earthquakes/${id}`, {
      method: "DELETE",
    });

    setEarthquakes((prev) => prev.filter((eq) => eq.id !== id));
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div className="container mt-4">

      
      <div className="text-center mb-4">
        <h2 className="fw-bold">Earthquake Monitor</h2>
      
      </div>

      
      <div className="d-flex gap-2 mb-3 justify-content-center">
        <button className="btn btn-success" onClick={fetchNewData}>
          Fetch Latest
        </button>

      </div>

  
      {loading ? (
        <div className="text-center">
          <div className="spinner-border text-primary" role="status"></div>
          <p className="mt-2">Loading earthquakes...</p>
        </div>
      ) : (
        <div className="table-responsive">
          <table className="table table-striped table-hover align-middle">

            <thead className="table-dark">
              <tr>
                <th>ID</th>
                <th>Magnitude</th>
                <th>Place</th>
                <th>Title</th>
                <th>Time</th>
                <th>Action</th>
              </tr>
            </thead>

            <tbody>
              {earthquakes.length === 0 ? (
                <tr>
                  <td colSpan="6" className="text-center text-muted">
                    No earthquake data available
                  </td>
                </tr>
              ) : (
                earthquakes.map((eq) => (
                  <tr key={eq.id}>
                    <td>{eq.id}</td>

                    <td>
                      <span
                        className={`badge ${
                          eq.magnitude > 4
                            ? "bg-danger"
                            : eq.magnitude > 2.5
                            ? "bg-warning text-dark"
                            : "bg-success"
                        }`}
                      >
                        {eq.magnitude}
                      </span>
                    </td>

                    <td>{eq.place}</td>
                    <td>{eq.title}</td>
                    <td>{new Date(eq.time).toLocaleString()}</td>

                    <td>
                      <button
                        className="btn btn-sm btn-outline-danger"
                        onClick={() => deleteEarthquake(eq.id)}
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default App;