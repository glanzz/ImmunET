"use client";
import { useState } from "react";
import { toast, ToastContainer } from "react-toastify";

const CreateDoctor = () => {
  const [formData, setFormData] = useState({
    name: "",
    password: "",
    rePassword: "",
    contact: "",
    address: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`${process.env.SERVER_API}/doctors`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        throw new Error("Failed to create doctor");
      }

      const responseData = await response.json();
      console.log("Created doctor:", responseData);

      // Show success toast
      toast.success("Doctor created successfully");
    } catch (error) {
      console.error("Error creating doctor:", error);
      // Show error toast
      toast.error("Failed to create doctor");
    }
  };

  return (
    <div>
      {/* top section design */}
      <div className="flex sm:gap-6 gap:6 items-center sm:mb-0 mb-4 px-4 pb-5 mt-5">
        <div className="sm:mr-0 mr-4 ">
          <h2 className="font-bold sm:text-lg text-sm uppercase text-bold_text_colour">
            {" "}
            Add Doctor
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
                  ADD DOCTOR
                </h2>
              </div>
              <div className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4 flex flex-col">
                <div className="-mx-3 md:flex mb-2 mt-4">
                  <div className="md:w-full px-3">
                    <label className="uppercase tracking-wide text-black text-xs font-bold mb-2">
                      Name
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="application-link"
                      type="text"
                      placeholder="Enter Name"
                      name="name"
                      value={formData.name}
                      onChange={handleChange}
                    />
                  </div>
                </div>
                <div className="-mx-3 md:flex mb-6">
                  <div className="md:w-1/2 px-3 mb-6 md:mb-0">
                    <label className="uppercase  text-black text-xs font-bold mb-2">
                      Password
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="company"
                      type="password"
                      placeholder="Password"
                      name="password"
                      value={formData.password}
                      onChange={handleChange}
                    />
                  </div>
                  <div className="md:w-1/2 px-3">
                    <label className="uppercase tracking-wide text-black text-xs font-bold mb-2">
                      Re-Password
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="title"
                      type="password"
                      placeholder="Re-password"
                      name="rePassword"
                      value={formData.rePassword}
                      onChange={handleChange}
                    />
                  </div>
                </div>
                <div className="-mx-3 md:flex mb-6">
                  <div className="md:w-1/2 px-3 mb-6 md:mb-0">
                    <label className="uppercase  text-black text-xs font-bold mb-2">
                      Address
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="company"
                      type="text"
                      placeholder="Address"
                      name="address"
                      value={formData.address}
                      onChange={handleChange}
                    />
                    {/*  <div>
                      <span className="text-red-500 text-xs italic">
                        Please fill out this field.
                      </span>
                    </div> */}
                  </div>
                  <div className="md:w-1/2 px-3">
                    <label className="uppercase tracking-wide text-black text-xs font-bold mb-2">
                      Contact
                    </label>
                    <input
                      className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
                      id="title"
                      type="text"
                      placeholder="Contact"
                      name="contact"
                      value={formData.contact}
                      onChange={handleChange}
                    />
                  </div>
                </div>

                <div className="mt-2">
                  <button
                    type="submit"
                    className="bg-custom_button_color w-full  text-black uppercase font-semibold  relative h-[50px] border px-3 transition-all before:absolute before:bottom-0 before:left-0 before:top-0 before:w-full before:h-0 before:bg-primary_colour before:transition-all before:duration-700 hover:text-white  hover:before:top-0 hover:before:h-full"
                  >
                    <span className="relative z-10">Continue</span>
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
      {/* Toast container for displaying messages */}
      <ToastContainer />
    </div>
  );
};

export default CreateDoctor;
