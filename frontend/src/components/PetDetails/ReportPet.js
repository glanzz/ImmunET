import { useState } from "react";

export default function ReportPet({ pet }) {
  const [completedSchedules, setCompletedSchedules] = useState([]);
  const [loading, setLoading] = useState(false);
  const markAsComplete = async (scheduleId) => {
    try {
      setLoading(true);
      const response = await fetch(
        `https://immunet.azurewebsites.net/doctors/1/pets/${pet.id}/schedules/${scheduleId}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ status: "COMPLETED" }),
        }
      );
      if (!response.ok) {
        throw new Error("Failed to mark schedule as complete");
      }
      // Update completedSchedules state to reflect the change
      setCompletedSchedules([...completedSchedules, scheduleId]);
      setLoading(false);
    } catch (error) {
      console.error("Error marking schedule as complete:", error);
    }
  };
  console.log("pet popup", pet);
  return (
    <div>
      <div className="bg-primary_colour p-4">
        <h2 className="text-lg font-bold text-white uppercase text-center">
          Recommended Immunization Schedule Report
        </h2>
      </div>

      <div className="container mx-auto px-4 sm:px-8">
        <div className="py-8">
          <div className="-mx-4 sm:-mx-8 px-4 sm:px-8 py-4 overflow-x-auto">
            <div className="inline-block min-w-full shadow-md rounded-lg overflow-hidden">
              <table className="min-w-full leading-normal">
                <thead>
                  <tr>
                    <th className="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-700 uppercase tracking-wider">
                      Name
                    </th>
                    <th className="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-700 uppercase tracking-wider">
                      Status
                    </th>
                    <th className="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-700 uppercase tracking-wider">
                      Scheduled Date
                    </th>
                    <th className="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-700 uppercase tracking-wider">
                      Administered Date
                    </th>
                    <th className="px-5 py-3 border-b-2 border-gray-200 bg-gray-100"></th>
                  </tr>
                </thead>
                <tbody>
                  {loading ? (
                    <p className="mx-auto text-center justify-center items-center uppercase">
                      Loading...
                    </p>
                  ) : (
                    <>
                      {pet?.report?.records[0]?.schedules?.map((data) => (
                        <tr key={data.id}>
                          <td className="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                            <div className="flex">
                              <div className="ml-3">
                                <p className="text-gray-900 whitespace-no-wrap">
                                  {pet?.report?.records[0]?.name}
                                </p>
                              </div>
                            </div>
                          </td>
                          <td className="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                            <p
                              className={`whitespace-no-wrap ${
                                data.status !== "PENDING"
                                  ? "text-green-500"
                                  : "text-red-500"
                              }`}
                            >
                              {data.status}
                            </p>
                          </td>
                          <td className="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                            <div>
                              <p className="text-gray-900 whitespace-no-wrap">
                                {new Date(
                                  data.scheduledDate
                                ).toLocaleDateString()}
                              </p>
                            </div>
                          </td>
                          <td className="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                            <div>
                              {data.administeredDate ? (
                                <p className="text-gray-900 whitespace-no-wrap">
                                  {new Date(
                                    data.administeredDate
                                  ).toLocaleDateString()}
                                </p>
                              ) : (
                                <p></p>
                              )}
                            </div>
                          </td>
                          <td className="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                            <div>
                              {!completedSchedules.includes(data.id) && (
                                <button
                                  className="text-white p-2 bg-gray-500 rounded-md whitespace-no-wrap"
                                  onClick={() => markAsComplete(data.id)}
                                >
                                  Mark As Complete
                                </button>
                              )}
                            </div>
                          </td>
                        </tr>
                      ))}
                    </>
                  )}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
