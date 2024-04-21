"use client";
import { useState } from "react";

const AddVaccineSchedule = () => {
  const [response, setResponse] = useState(null);

  const handleSubmit = async (event) => {
    event.preventDefault();
    const vaccineId = event.target.vaccine_id.value;
    console.log("vaccineId", vaccineId);
    try {
      const response = await fetch(
        `${process.env.SERVER_API}/doctors/1/pets/1/immunizations/add`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            vaccine_id: vaccineId,
          }),
        }
      );
      const data = await response.json();
      setResponse(data);
    } catch (error) {
      console.error("Error adding vaccine schedule:", error);
    }
  };
  console.log("response", response);

  return (
    <div className="">
      {/* top section design */}
      <div className="flex sm:gap-6 gap:6 items-center sm:mb-0 mb-4 px-4 pb-5 mt-5">
        <div className="sm:mr-0 mr-4 ">
          <h2 className="font-bold sm:text-lg text-sm uppercase text-bold_text_colour">
            {" "}
            Add Vaccine Schedule
          </h2>
        </div>
      </div>
      {/* top section design */}

      <div className="lg:grid lg:grid-cols-9">
        <div className="col-span-7">
          <div className=" mx-auto  px-12 lg:px-20">
            <form onSubmit={handleSubmit}>
              <div className="bg-primary_colour py-5">
                <h2 className="text-center text-xl text-white font-semibold">
                  ADD VACCINE SCHEDULE
                </h2>
              </div>
              <div className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4 flex flex-col">
                <div className="-mx-3 md:flex mb-2 mt-4">
                  <div className="md:w-full px-3">
                    <label
                      className="uppercase tracking-wide text-black text-xs font-bold mb-2"
                      htmlFor="vaccine_id"
                    >
                      Vaccine ID
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="vaccine_id"
                      type="text"
                      name="vaccine_id"
                      placeholder="Enter vaccine ID"
                      required
                    />
                  </div>
                </div>

                <div className="mt-2">
                  <button
                    type="submit"
                    className="bg-custom_button_color w-full text-black uppercase font-semibold relative h-[50px] border px-3 transition-all before:absolute before:bottom-0 before:left-0 before:top-0 before:w-full before:h-0 before:bg-primary_colour before:transition-all before:duration-700 hover:text-white hover:before:top-0 hover:before:h-full"
                  >
                    <span className="relative z-10">Continue</span>
                  </button>
                </div>
              </div>
            </form>
            {/* Display response */}
            {response && (
              <div className="bg-gray-300 p-5 mx-auto">
                <div className="grid grid-cols-2 gap-4">
                  <div className="col-span-1 text-left items-start">
                    <h2 className="text-left justify-start items-start">
                      <span className="font-semibold uppercase">Name:</span>
                      {response.name}
                    </h2>
                  </div>
                  <div className="col-span-1">
                    <h2 className="text-left justify-start items-start text-xl">
                      <span className="font-semibold uppercase">Type:</span>
                      {response.type}
                    </h2>
                  </div>
                  <div className="col-span-1">
                    <h2 className="text-left justify-start items-start text-xl">
                      <span className="font-semibold uppercase">
                        Scheduled Date:
                      </span>
                      {response.schedules[0].scheduled_date}
                    </h2>
                  </div>
                  <div className="col-span-1">
                    <h2 className="text-left justify-start items-start text-xl">
                      <span className="font-semibold uppercase">
                        Administered Date:
                      </span>
                      {response.schedules[0].administered_date}
                    </h2>
                  </div>
                  <div className="col-span-1">
                    <h2 className="text-left justify-start items-start text-xl">
                      <span className="font-semibold uppercase">Status:</span>
                      {response.schedules[0].status}
                    </h2>
                  </div>
                  <div className="col-span-1">
                    <h2 className="text-left justify-start items-start text-xl">
                      <span className="font-semibold uppercase">Doctor:</span>
                      {response.schedules[0].doctor}
                    </h2>
                  </div>
                </div>
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddVaccineSchedule;
