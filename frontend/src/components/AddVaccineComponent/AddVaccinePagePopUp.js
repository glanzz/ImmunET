import { useEffect } from "react";
import { ImCross } from "react-icons/im";

export default function AddVaccinePagePopUp({ isOpen, onClose }) {
  useEffect(() => {
    // Add a class to the body when the modal is open
    if (isOpen) {
      document.body.classList.add("modal-open");
    } else {
      document.body.classList.remove("modal-open");
    }

    // Cleanup: Remove the class when the component unmounts
    return () => {
      document.body.classList.remove("modal-open");
    };
  }, [isOpen]);

  return (
    <>
      {isOpen && (
        <div
          className={`fixed inset-0 bg-black bg-opacity-50 backdrop-filter backdrop-blur-sm z-40  ${
            isOpen ? "scale-100 duration-700" : "scale-50 duration-700"
          }  `}
        ></div>
      )}
      <div
        className={`fixed z-50 top-1/2 md:left-1/2 left-0 transform md:-translate-x-1/2 md:-translate-y-1/2  bg-gray-400 p-6 rounded-md ${
          isOpen
            ? "opacity-100 scale-100 duration-700"
            : "opacity-0 scale-50 duration-700"
        } transition-opacity transition-scale`}
      >
        <div className="relative w-full">
          <button
            onClick={onClose}
            className="absolute top-0 right-0 p-2 text-primary_colour"
          >
            <ImCross />
          </button>
          <h2 className="text-lg font-bold uppercase text-center">Add stuff</h2>
          <div className="md:w-full px-3">
            <label
              className="uppercase tracking-wide text-black text-xs font-bold mb-2"
              htmlFor="application-link"
            >
              stuff name
            </label>
            <input
              className="w-full bg-gray-200 text-black border border-gray-200 rounded py-3 px-4 mb-3"
              id="application-link"
              type="text"
              placeholder="stuff name"
            />
          </div>
        </div>
      </div>
    </>
  );
}
